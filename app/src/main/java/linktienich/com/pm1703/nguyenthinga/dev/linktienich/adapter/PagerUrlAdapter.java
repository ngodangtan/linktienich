package linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.Conts;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.DatabaseManager;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.R;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.activity.MainActivity;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.dialog.DialogBrowserDefault;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.item.ItemUrl;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.item.ItemViewPager;

/**
 * Created by Hoang on 6/28/2016.
 */
public class PagerUrlAdapter extends android.support.v4.view.PagerAdapter {

    private static final String TAG = "PagerUrlAdapter";
    private GridView gridViewUrl;
    private ArrayList<ItemViewPager> arrItemPager = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;


    private DatabaseManager databaseManager;
    private MainActivity mainActivity;
    private DialogBrowserDefault dialogBrowserDefault;
    private ListUrlGridAdapter listUrlGridAdapter;

    public PagerUrlAdapter(Context context, MainActivity mainActivity) {
        this.context = context;
        this.mainActivity = mainActivity;
        inflater = LayoutInflater.from(context);
        databaseManager = new DatabaseManager(context);
        dialogBrowserDefault = new DialogBrowserDefault(context, false);
        dialogBrowserDefault.setMainActivity(mainActivity);
        initData();
    }

    public ArrayList<ItemViewPager> initData() {
        ArrayList<ItemUrl> arrItemUrl = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl1 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl2 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl3 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl4 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl5 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl6 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl7 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl8 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl9 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl10 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl11 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl12 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl13 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl14 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl15 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl16 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl17 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl18 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl19 = new ArrayList<>();
        ArrayList<ItemUrl> arrItemUrl20 = new ArrayList<>();

        arrItemUrl = databaseManager.getAllItemUrl(Conts.FIELD_FAVORITE, Conts.DEFAULT_VALUE_INT_1);
        for (int i=0;i<arrItemUrl.size(); i++){
            Log.i(TAG, "ID: "+arrItemUrl.get(i).getId());
        }
        arrItemUrl1 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_1);
        arrItemUrl2 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_2);
        arrItemUrl3 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_3);
        arrItemUrl4 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_4);
        arrItemUrl5 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_5);
        arrItemUrl6 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_6);
        arrItemUrl7 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_7);
        arrItemUrl8 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_8);
        arrItemUrl9 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_9);
        arrItemUrl10 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_10);
        arrItemUrl11 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_11);
        arrItemUrl12 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_12);
        arrItemUrl13 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_13);
        arrItemUrl14 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_14);
        arrItemUrl15 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_15);
        arrItemUrl16 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_16);
        arrItemUrl17 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_17);
        arrItemUrl18 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_18);
        arrItemUrl19 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_19);
        arrItemUrl20 = databaseManager.getAllItemUrl(Conts.FIELD_TYPE, Conts.DEFAULT_VALUE_INT_20);

        arrItemPager.add(new ItemViewPager("BÁO YÊU THÍCH", arrItemUrl));
        arrItemPager.add(new ItemViewPager("Tin Tức", arrItemUrl1));
        arrItemPager.add(new ItemViewPager("Tin Tức Giải Trí", arrItemUrl2));
        arrItemPager.add(new ItemViewPager("Nghe Nhạc", arrItemUrl3));
        arrItemPager.add(new ItemViewPager("Video - Film - Clip", arrItemUrl4));
        arrItemPager.add(new ItemViewPager("Game", arrItemUrl5));
        arrItemPager.add(new ItemViewPager("Mạnh xã hội - Diễn đàn", arrItemUrl6));
        arrItemPager.add(new ItemViewPager("Phụ nữ - Gia đình", arrItemUrl7));
        arrItemPager.add(new ItemViewPager("Du lịch", arrItemUrl8));
        arrItemPager.add(new ItemViewPager("Nhiếp ảnh", arrItemUrl9));
        arrItemPager.add(new ItemViewPager("Thể thao", arrItemUrl10));
        arrItemPager.add(new ItemViewPager("Tài chính kinh doanh", arrItemUrl11));
        arrItemPager.add(new ItemViewPager("Thương mại điện tử", arrItemUrl12));
        arrItemPager.add(new ItemViewPager("Bất động sản", arrItemUrl13));
        arrItemPager.add(new ItemViewPager("Việc làm", arrItemUrl14));
        arrItemPager.add(new ItemViewPager("Ô tô - Xe máy", arrItemUrl15));
        arrItemPager.add(new ItemViewPager("Máy tính - Công nghệ", arrItemUrl16));
        arrItemPager.add(new ItemViewPager("Sức khỏe", arrItemUrl17));
        arrItemPager.add(new ItemViewPager("Học tập - Tài liệu", arrItemUrl18));
        arrItemPager.add(new ItemViewPager("Đọc chuyện", arrItemUrl19));
        arrItemPager.add(new ItemViewPager("Dịch vụ tiện ích", arrItemUrl20));
        for (int i = 0; i < arrItemUrl1.size(); i++) {
            Log.i(TAG, "" + i + "_ " + arrItemUrl1.get(i).getTitleUrl());
        }
        return arrItemPager;

    }

    @Override
    public int getCount() {
        return arrItemPager.size();
    }

    @Override
    public boolean isViewFromObject(View currentView, Object nextView) {
        return currentView.equals(nextView);
    }

    @Override
    public Object instantiateItem(ViewGroup viewPager, int position) {
        View view;
        if (position == Conts.DEFAULT_VALUE_INT_0) {
            view = inflater.inflate(R.layout.yeu_thich, null);
            gridViewUrl = (GridView) view.findViewById(R.id.grid_view_list_yeu_thich);
            listUrlGridAdapter = new ListUrlGridAdapter(context, arrItemPager.get(position).getArrItemUrl(), mainActivity);
            gridViewUrl.setAdapter(listUrlGridAdapter);
            gridViewUrl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(listUrlGridAdapter.getItem(position).getId() == Conts.DEFAULT_VALUE_INT_9999){
                        mainActivity.startActivitySearch(false);
                    }else {
                        boolean isShowDialog = readInforSettingPopupSMS(Conts.KEY_SETTING_SAVE_DEFAULT_BROWSER, false);
                        String url = listUrlGridAdapter.getItem(position).getUrl();
                        if (isShowDialog) {
                            if (readInforSettingPopupSMS(Conts.KEY_BROWSER_CURRENT, false)) {
                                mainActivity.showWebViewActivity(url);
                            } else if (readInforSettingPopupSMS(Conts.KEY_BROWSER_DEFAULT, false)) {
                                mainActivity.goToBrowserDefault(url);
                            }
                        } else {
                            dialogBrowserDefault.setUrl(url);
                            dialogBrowserDefault.show();
                        }
                    }
                }
            });
        } else {
            view = inflater.inflate(R.layout.item_pager, null);
            //TextView tvTopic = (TextView)view.findViewById(R.id.tv_topic);
            ListView lvItemUrl = (ListView) view.findViewById(R.id.lv_item_url);

            ItemViewPager item = arrItemPager.get(position);

            //tvTopic.setText(item.getTopic());
            final ListUrlAdapter urlAdapter = new ListUrlAdapter(context, item.getArrItemUrl());
            urlAdapter.setPagerUrlAdapter(PagerUrlAdapter.this);
            //urlAdapter.setArrItemUrl(item.getArrItemUrl());

            lvItemUrl.setAdapter(urlAdapter);
            lvItemUrl.deferNotifyDataSetChanged();
            lvItemUrl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    boolean isShowDialog = readInforSettingPopupSMS(Conts.KEY_SETTING_SAVE_DEFAULT_BROWSER, false);
                    String url = urlAdapter.getItem(position).getUrl();
                    if (isShowDialog) {
                        if (readInforSettingPopupSMS(Conts.KEY_BROWSER_CURRENT, false)) {
                            mainActivity.showWebViewActivity(url);
                        } else if (readInforSettingPopupSMS(Conts.KEY_BROWSER_DEFAULT, false)) {
                            mainActivity.goToBrowserDefault(url);
                        }
                    } else {
                        dialogBrowserDefault.setUrl(url);
                        dialogBrowserDefault.show();
                    }
                }
            });
        }
        viewPager.addView(view);

        return view;
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        }
    };

    @Override
    public void destroyItem(ViewGroup viewPager, int position, Object view) {
        viewPager.removeView((View) view);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public ArrayList<ItemViewPager> getArrItemPager() {
        return arrItemPager;
    }

    public boolean readInforSettingPopupSMS(String key, boolean valueDefault) {
        SharedPreferences preferences = context.getSharedPreferences(Conts.FILENAME_SETTING, 1);
        return preferences.getBoolean(key, valueDefault);
    }

    public void setPosition(int position) {

    }

    public void showSnackBarOnInsertSuccess(int isSuccess) {
        mainActivity.showSnackBarOnInsertSuccess(isSuccess);
    }

    public void showSnackBarOnAddSuccess(boolean isSuccess) {
        mainActivity.showSnackBarOnAddSuccess(isSuccess);
    }

    public void notifyDataSetChangedURL() {
        mainActivity.notifyDataSetChangedURL();
    }

    ;

    public void showSnackBarOnDeleteSuccess(int isSuccess, String titleUrl) {
        mainActivity.showSnackBarOnDeleteSuccess(isSuccess, titleUrl);
    }

    public void removeItemFavorite(int id) {
        int size = listUrlGridAdapter.getArrItemUrl().size();
        for (int index = 0; index < size; index++) {
            if (listUrlGridAdapter.getArrItemUrl().get(index).getId() == id) {
                listUrlGridAdapter.getArrItemUrl().remove(index);
            }
        }
        listUrlGridAdapter.notifyDataSetChanged();
    }

}
