package linktienich.com.pm1703.nguyenthinga.dev.linktienich.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.Conts;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.DatabaseManager;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.R;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.activity.MainActivity;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter.ListUrlAdapter;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.item.ItemUrl;

/**
 * Created by Hoang on 7/2/2016.
 */
public class DialogOptionUrl extends Dialog implements View.OnClickListener {

    private static final String TAG = "DialogOptionUrl";
    private boolean isAddOrUpdate;
    private EditText edtTitleUrl, edtUrl;
    private TextView tvDanhMuc;
    private TextView tvTitleDialog;
    private Spinner spinDanhMuc;
    private ImageView imvFavorite;
    private Button btnAddOrUpdate, btnCancel;


    private Context context;
    private DatabaseManager databaseManager;
    private ListUrlAdapter listUrlAdapter;

    private boolean isFavorite;
    private int id;
    private int favorite = 0;
    String arr[] = new String[]{
            "Tin Tức",
            "Tin tức giải trí",
            "Nghe nhạc",
            "Video - Film - Clip",
            "Game",
            "Mạng xã hội - Diễn đàn",
            "Phụ nữ - Gia đình",
            "Du lịch",
            "Nhiếp ảnh",
            "Thể thao",
            "Tài chính kinh doanh",
            "Thương mại điện tử",
            "Bất động sản",
            "Việc làm",
            "Ô tô - Xe máy",
            "Máy tính - Công nghệ",
            "Sức khỏe",
            "Học tập - Tài liệu",
            "Đọc truyện",
            "Dịch vụ tiện ích",
    };
    private int type;
    private int enableDelete;

    public DialogOptionUrl(Context context, ListUrlAdapter listUrlAdapter, boolean isAddOrUpdate) {
        super(context);
        this.context = context;
        this.listUrlAdapter = listUrlAdapter;
        this.isAddOrUpdate = isAddOrUpdate;
        //this.mainActivity = mainActivity;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_url_option);
        initView(isAddOrUpdate);
        databaseManager = new DatabaseManager(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView(final boolean isAddOrUpdate) {
        isFavorite = false;
        edtTitleUrl = (EditText) findViewById(R.id.edt_title_url);
        edtUrl = (EditText) findViewById(R.id.edt_url);

        tvDanhMuc = (TextView) findViewById(R.id.tv_danh_muc);
        tvTitleDialog = (TextView) findViewById(R.id.tv_title_dialog);
        spinDanhMuc = (Spinner) findViewById(R.id.spinner_danh_muc);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDanhMuc.setAdapter(adapter);
        spinDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isAddOrUpdate){
                    DialogOptionUrl.this.type = position + Conts.DEFAULT_VALUE_INT_1;
                    if (spinDanhMuc.isEnabled()) {
                        tvDanhMuc.setText(arr[position]);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imvFavorite = (ImageView) findViewById(R.id.imv_favorite);
        imvFavorite.setOnClickListener(this);

        btnAddOrUpdate = (Button) findViewById(R.id.btn_add_or_update);

        if (isAddOrUpdate) {
            btnAddOrUpdate.setText("THÊM");
            tvTitleDialog.setText("Thêm mới");
        } else {
            btnAddOrUpdate.setText("CẬP NHẬT");
            tvTitleDialog.setText("Tùy chỉnh");
        }


        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnAddOrUpdate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_or_update:
                if (edtTitleUrl.getText().toString().equals(Conts.BLANK)) {
                    Toast.makeText(context, "Vui lòng nhập TÊN ĐƯỜNG DẪN", Toast.LENGTH_SHORT).show();
                } else if (edtUrl.getText().toString().equals(Conts.BLANK)) {
                    Toast.makeText(context, "Vui lòng nhập ĐỊA CHỈ ĐƯỜNG DẪN", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isAddOrUpdate) {
                        int isSuccess = databaseManager.updateURL(id, edtTitleUrl.getText().toString(),
                                edtUrl.getText().toString(), getType(), getFavorite(), enableDelete);
                        listUrlAdapter.notifyDataSetChangedURL();
                        listUrlAdapter.showSnackBarOnInsertSuccess(isSuccess);
                    } else {
                        ArrayList<ItemUrl> arrItem = databaseManager.getAllItem();
                        int sizeTemp = arrItem.size() - 2;
                        int idTemp = arrItem.get(sizeTemp).getId();
                        int idFinal = idTemp + 1;
                        boolean isSuccessAdd = databaseManager.insertData(idFinal, edtTitleUrl.getText().toString(),
                                edtUrl.getText().toString(), type, getFavorite(), Conts.DEFAULT_VALUE_INT_1);

//                        Log.i(TAG, "Title: " + edtTitleUrl.getText().toString());
//                        Log.i(TAG, "URL: " + edtUrl.getText().toString());
//                        Log.i(TAG, "type: " + type);
//                        Log.i(TAG, "favorite: " + favorite);
                        listUrlAdapter.notifyDataSetChangedURL();
                        listUrlAdapter.showSnackBarOnAddSuccess(isSuccessAdd);
                    }
                    dismiss();
                }
                break;
            case R.id.btn_cancel:
                clearData();
                dismiss();
                break;
            case R.id.imv_favorite:
                if (!isFavorite) {
                    isFavorite = true;
                    imvFavorite.setImageResource(R.drawable.ic_favorite_press);
                    setFavorite(Conts.DEFAULT_VALUE_INT_1);
                } else {
                    isFavorite = false;
                    imvFavorite.setImageResource(R.drawable.ic_favorite_nomarl);
                    setFavorite(Conts.DEFAULT_VALUE_INT_0);
                }
                break;
        }
    }

    public void setDataUrl(int id, String titleUrl, String url, int type, int favorite, int enableDelete) {
        this.id = id;
        this.enableDelete = enableDelete;
        this.type=type;
        edtTitleUrl.setText(titleUrl);
        edtUrl.setText(url);
        tvDanhMuc.setText(arr[type - Conts.DEFAULT_VALUE_INT_1]);
//        for (int index = 0; index < arr.length; index++) {
//            if (arr[index].equals(tvDanhMuc.getText().toString())) {
//                this.type = index + Conts.DEFAULT_VALUE_INT_1;
//                Log.i(TAG, "type: " + this.type);
//            }
//        }
        spinDanhMuc.setEnabled(false);
        if (favorite == Conts.DEFAULT_VALUE_INT_1) {
            isFavorite = true;
            imvFavorite.setImageResource(R.drawable.ic_favorite_press);
        } else if (favorite == Conts.DEFAULT_VALUE_INT_0) {
            isFavorite = false;
            imvFavorite.setImageResource(R.drawable.ic_favorite_nomarl);
        }
    }

    public void clearData() {
        edtTitleUrl.setText(Conts.BLANK);
        edtUrl.setText(Conts.BLANK);
        imvFavorite.setImageResource(R.drawable.ic_favorite_nomarl);
        setFavorite(Conts.DEFAULT_VALUE_INT_0);
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
