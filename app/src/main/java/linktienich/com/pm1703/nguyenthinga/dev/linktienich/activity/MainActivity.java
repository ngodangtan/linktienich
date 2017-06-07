package linktienich.com.pm1703.nguyenthinga.dev.linktienich.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.audiofx.BassBoost;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.Conts;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.R;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter.ListUrlAdapter;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter.MenuAdapter;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter.PagerUrlAdapter;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.dialog.DialogOpenNetwork;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.dialog.DialogOptionUrl;

public class MainActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemClickListener,
        ViewPager.OnPageChangeListener {
    private static final String TAG = "MainActivity";

    private ListView lvMenu;
    private ViewPager viewPager;
    private ImageView imvMenu, imvSearch;
    private TextView tvTopic;


    private MenuAdapter menuAdapter;
    private PagerUrlAdapter pagerAdapter;
    private DialogOptionUrl dialogOptionUrl;
    private DialogOpenNetwork dialogOpenNetwork;
    private ListUrlAdapter listUrlAdapter;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        listUrlAdapter = new ListUrlAdapter(this, pagerAdapter.getArrItemPager().get(position).getArrItemUrl());
        listUrlAdapter.setPagerUrlAdapter(pagerAdapter);
        dialogOptionUrl = new DialogOptionUrl(this, listUrlAdapter, true);
        dialogOpenNetwork = new DialogOpenNetwork(this, this);
    }

    private void initView() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogOptionUrl.clearData();
                dialogOptionUrl.show();
            }
        });
        menuAdapter = new MenuAdapter(this);
        lvMenu = (ListView) findViewById(R.id.lv_menu);
        lvMenu.setAdapter(menuAdapter);
        lvMenu.setOnItemClickListener(this);

        imvMenu = (ImageView) findViewById(R.id.imv_menu);
        imvMenu.setOnClickListener(this);
        imvSearch = (ImageView) findViewById(R.id.imv_search);
        imvSearch.setOnClickListener(this);


        viewPager = (ViewPager) findViewById(R.id.view_pager_list_url);
        pagerAdapter = new PagerUrlAdapter(this, this);
        //pagerAdapter.setMainActivity(MainActivity.this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);

        tvTopic = (TextView) findViewById(R.id.tv_topic);

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkNetwork();
        notifyDataSetChangedURL();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imv_menu:
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.imv_search:
                startActivitySearch(true);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        viewPager.setCurrentItem(position);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    protected void onDestroy() {
        writeSetting();
        super.onDestroy();
        Log.i(TAG, "OnDestroy_GiapMN");
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "OnStop_GiapMN");
        super.onStop();
    }

    private void checkNetwork() {
        if (!checkConnectNetwork()) {
            dialogOpenNetwork.show();
        }
    }

    public boolean checkConnectNetwork() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;
        return true;
    }

    public void writeSetting() {
        SharedPreferences pref = getSharedPreferences(Conts.FILENAME_SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Conts.KEY_SETTING_SAVE_DEFAULT_BROWSER, false);
        editor.putBoolean(Conts.KEY_BROWSER_DEFAULT, false);
        editor.putBoolean(Conts.KEY_BROWSER_CURRENT, false);
        editor.apply();

    }

    public void showWebViewActivity(String url) {
        Intent intent = new Intent();
        intent.setClass(this, WebViewActivity.class);
        intent.putExtra(Conts.KEY_URL, url);
        startActivity(intent);
    }

    public void goToBrowserDefault(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private void runAnimation(TextView textView) {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.animation_text_view);
        a.reset();
        textView.clearAnimation();
        textView.startAnimation(a);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tvTopic.setText(pagerAdapter.getArrItemPager().get(position).getTopic());
        runAnimation(tvTopic);
        this.position = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void showSnackBarOnInsertSuccess(int isSuccess) {
        if (isSuccess == Conts.DEFAULT_VALUE_INT_1) {
            Snackbar.make(findViewById(android.R.id.content), "Cập nhật THÀNH CÔNG", Snackbar.LENGTH_LONG)
                    .show();
        } else {
            Snackbar.make(findViewById(android.R.id.content), "Cập nhật THẤT BẠI", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    public void showSnackBarOnAddSuccess(boolean isSuccess) {
        if (isSuccess) {
            Snackbar.make(findViewById(android.R.id.content), "Thêm mới THÀNH CÔNG", Snackbar.LENGTH_LONG)
                    .show();
        } else {
            Snackbar.make(findViewById(android.R.id.content), "Thêm mới THẤT BẠI", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    public void showSnackBarOnDeleteSuccess(int isSuccess, String titleUrl) {
        if (isSuccess == Conts.DEFAULT_VALUE_INT_1) {
            Snackbar.make(findViewById(android.R.id.content), "Đã xóa " + titleUrl + " thành công!", Snackbar.LENGTH_LONG)
                    .show();
        } else if (isSuccess == Conts.DEFAULT_VALUE_INT_99) {
            Snackbar.make(findViewById(android.R.id.content), "Không thể xóa " + titleUrl, Snackbar.LENGTH_LONG)
                    .show();
        } else {
            Snackbar.make(findViewById(android.R.id.content), "Xóa " + titleUrl + "thất bại!", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    public void notifyDataSetChangedURL() {
        pagerAdapter.getArrItemPager().clear();
        pagerAdapter.initData();
        pagerAdapter.notifyDataSetChanged();
    }

    public void startActivitySearch(boolean isSearch) {
        Intent intent = new Intent();
        intent.setClass(this, SearchActivity.class);
        intent.putExtra(Conts.KEY_SEARCH, isSearch);
        startActivity(intent);
    }
}
