package linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.Conts;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.R;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.activity.MainActivity;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.dialog.DialogBrowserDefault;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.item.ItemUrl;

/**
 * Created by Hoang on 6/28/2016.
 */
public class ListUrlGridAdapter extends BaseAdapter {
    private ArrayList<ItemUrl> arrItemUrl = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    private MainActivity mainActivity;
    private DialogBrowserDefault dialogBrowserDefault;

    ImageView imvImageUrl;
    TextView tvTitleUrl;

    public ListUrlGridAdapter(Context context, ArrayList<ItemUrl> arrItemUrl, MainActivity mainActivity) {
        this.context = context;
        this.arrItemUrl = arrItemUrl;
        this.mainActivity = mainActivity;
        inflater = LayoutInflater.from(context);
        dialogBrowserDefault = new DialogBrowserDefault(context, false);
        dialogBrowserDefault.setMainActivity(mainActivity);
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
//        if (position == (getCount() - 1)) {
//            view = inflater.inflate(R.layout.footer_layout_favorites, null);
//        } else {
        if (view == null) {
            view = inflater.inflate(R.layout.item_url_grid, null);
            imvImageUrl = (ImageView) view.findViewById(R.id.imv_image_url_grid);
            tvTitleUrl = (TextView) view.findViewById(R.id.tv_title_url_grid);
            //tvTitleUrl.setSelected(true);
        }
        ItemUrl item = arrItemUrl.get(position);

        String urlIcon = item.getUrlIcon();
        if (urlIcon == null) {
            Picasso.with(context).load(R.drawable.ic_add_newpaper).fit()
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_main_item)
                    .into(imvImageUrl);
        } else {
            Picasso.with(context).load(item.getUrlIcon())
                    .fit()
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_main_item)
                    .into(imvImageUrl);
        }
//        Picasso.with(context).load(Conts.URL + item.getId() + Conts.FORMAT_IMAGE)
//                .fit()
//                .placeholder(R.drawable.ic_loading)
//                .error(R.drawable.ic_main_item)
//                .into(imvImageUrl);
//        imvImageUrl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isShowDialog = readInforSettingPopupSMS(Conts.KEY_SETTING_SAVE_DEFAULT_BROWSER, false);
//                String url = getItem(position).getUrl();
//                if (isShowDialog) {
//                    if (readInforSettingPopupSMS(Conts.KEY_BROWSER_CURRENT, false)) {
//                        mainActivity.showWebViewActivity(url);
//                    } else if (readInforSettingPopupSMS(Conts.KEY_BROWSER_DEFAULT, false)) {
//                        mainActivity.goToBrowserDefault(url);
//                    }
//                } else {
//                    dialogBrowserDefault.setUrl(url);
//                    dialogBrowserDefault.show();
//                }
//            }
//        });
        tvTitleUrl.setText(item.getTitleUrl());

        //}

        return view;
    }

    public boolean readInforSettingPopupSMS(String key, boolean valueDefault) {
        SharedPreferences preferences = context.getSharedPreferences(Conts.FILENAME_SETTING, 1);
        return preferences.getBoolean(key, valueDefault);
    }


    public void setArrItemUrl(ArrayList<ItemUrl> arrItemUrl) {
        this.arrItemUrl = arrItemUrl;
    }

    public ArrayList<ItemUrl> getArrItemUrl() {
        return arrItemUrl;
    }
}
