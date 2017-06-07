package linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.Conts;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.DatabaseManager;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.R;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.dialog.DialogOptionUrl;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.item.ItemUrl;

/**
 * Created by Hoang on 6/28/2016.
 */
public class ListUrlAdapter extends BaseAdapter {

    private static final String TAG = "ListUrlAdapter";
    private ArrayList<ItemUrl> arrItemUrl = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    //private DialogOptionUrl dialogOptionUrl;
    private PagerUrlAdapter pagerUrlAdapter;
    private DatabaseManager databaseManager;

    String arr[] = {
            "Tùy chọn",
            "Xóa"};


    public ListUrlAdapter(Context context, ArrayList<ItemUrl> arrItemUrl) {
        this.context = context;
        this.arrItemUrl = arrItemUrl;
        inflater = LayoutInflater.from(context);
        databaseManager = new DatabaseManager(context);
        //dialogOptionUrl = new DialogOptionUrl(context);
    }

    @Override
    public int getCount() {
        return arrItemUrl.size();
    }

    @Override
    public ItemUrl getItem(int position) {
        return arrItemUrl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_url, null);
            holder = new ViewHolder();
            holder.imvImageUrl = (ImageView) view.findViewById(R.id.imv_image_url);
            holder.tvTitleUrl = (TextView) view.findViewById(R.id.tv_title_url);
            holder.tvUrl = (TextView) view.findViewById(R.id.tv_url);
            holder.imvOption = (ImageView) view.findViewById(R.id.imv_drop_menu);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ItemUrl item = arrItemUrl.get(position);

        Picasso.with(context).load(item.getUrlIcon())
                .fit()
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_main_item)
                .into(holder.imvImageUrl);

//        Picasso.with(context).load(Conts.URL + item.getId() + Conts.FORMAT_IMAGE)
//                .fit()
//                .placeholder(R.drawable.ic_loading)
//                .error(R.drawable.ic_main_item)
//                .into(holder.imvImageUrl);
        holder.tvTitleUrl.setText(item.getTitleUrl());
        holder.tvTitleUrl.setSelected(true);
        holder.tvUrl.setText(item.getUrl());
        holder.imvOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = arrItemUrl.get(position).getId();
                String titleUrl = arrItemUrl.get(position).getTitleUrl();
                String url = arrItemUrl.get(position).getUrl();
                int type = arrItemUrl.get(position).getType();
                int favorite = arrItemUrl.get(position).getYeuThich();
                int enableDelete = arrItemUrl.get(position).getEnableDelete();
                showMenuPopup(v, id, titleUrl, url, type, favorite, enableDelete, position);
            }
        });
        return view;
    }

    public void notifyDataSetChangedURL() {
        pagerUrlAdapter.notifyDataSetChangedURL();
        notifyDataSetChanged();
    }

    private class ViewHolder {
        ImageView imvImageUrl;
        TextView tvTitleUrl;
        TextView tvUrl;
        ImageView imvOption;
    }

    private void showMenuPopup(View v, final int id, final String titleUrl, final String url,
                               final int type, final int favorite, final int enableDelete, final int possition) {
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_option:
                        DialogOptionUrl dialogOptionUrl = new DialogOptionUrl(context, ListUrlAdapter.this, false);
                        dialogOptionUrl.setDataUrl(id, titleUrl, url, type, favorite, enableDelete);
                        dialogOptionUrl.show();
                        break;
                    case R.id.menu_delete:
                        if (enableDelete == Conts.DEFAULT_VALUE_INT_0) {
                            pagerUrlAdapter.showSnackBarOnDeleteSuccess(Conts.DEFAULT_VALUE_INT_99, titleUrl);
                        } else {
                            int isSuccess = databaseManager.deleteUrl(id);
                            Log.i(TAG, "isSuccee: " + isSuccess);
                            pagerUrlAdapter.notifyDataSetChangedURL();
                            pagerUrlAdapter.showSnackBarOnDeleteSuccess(isSuccess, titleUrl);
                            notifyDataSetChanged();

                        }
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    public void showSnackBarOnInsertSuccess(int isSuccess) {
        pagerUrlAdapter.showSnackBarOnInsertSuccess(isSuccess);
    }

    public void showSnackBarOnAddSuccess(boolean isSuccess) {
        pagerUrlAdapter.showSnackBarOnAddSuccess(isSuccess);
    }

    public ArrayList<ItemUrl> getArrItemUrl() {
        return arrItemUrl;
    }

    public void setPagerUrlAdapter(PagerUrlAdapter pagerUrlAdapter) {
        this.pagerUrlAdapter = pagerUrlAdapter;
    }

}
