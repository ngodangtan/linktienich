package linktienich.com.pm1703.nguyenthinga.dev.linktienich.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.Conts;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.DatabaseManager;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.R;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter.ListUrlAdapter;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter.PagerUrlAdapter;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.adapter.SearchAdapter;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.dialog.DialogBrowserDefault;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.item.ItemUrl;

/**
 * Created by Hoang on 7/12/2016.
 */
public class SearchActivity extends Activity implements AdapterView.OnItemClickListener{

    private ImageView imvBack;
    private EditText edtSearch;
    private ListView lvItemUrl;

    private SearchAdapter searchAdapter;
    private DatabaseManager databaseManager;

    private boolean isSearch;
    private DialogBrowserDefault dialogBrowserDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getValue();
        initView();
    }

    private void getValue() {
        Intent intent=getIntent();
        setSearch(intent.getBooleanExtra(Conts.KEY_SEARCH, false));

    }

    private void initView() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        databaseManager = new DatabaseManager(this);
        dialogBrowserDefault = new DialogBrowserDefault(this, true);
        dialogBrowserDefault.setSearchActivity(this);

        imvBack = (ImageView) findViewById(R.id.imv_back_search);
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchAdapter = new SearchAdapter(this, databaseManager.getAllItem());
        lvItemUrl = (ListView) findViewById(R.id.lv_search_url);
        lvItemUrl.setAdapter(searchAdapter);

        lvItemUrl.setOnItemClickListener(this);

        edtSearch =(EditText) findViewById(R.id.edt_input_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 0) {
                    searchAdapter.showAllItem();
                }
            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemUrl item = searchAdapter.getItem(position);
        if(isSearch()){
            boolean isShowDialog = readInforSettingPopupSMS(Conts.KEY_SETTING_SAVE_DEFAULT_BROWSER, false);
            String url = item.getUrl();
            if (isShowDialog) {
                if (readInforSettingPopupSMS(Conts.KEY_BROWSER_CURRENT, false)) {
                    showWebViewActivity(url);
                } else if (readInforSettingPopupSMS(Conts.KEY_BROWSER_DEFAULT, false)) {
                    goToBrowserDefault(url);
                }
            } else {
                dialogBrowserDefault.setUrl(url);
                dialogBrowserDefault.show();
            }
        }else {
            databaseManager.updateFavoritesURL(item.getId(), Conts.DEFAULT_VALUE_INT_1);
            finish();
        }
    }

    public boolean isSearch() {
        return isSearch;
    }

    public void setSearch(boolean search) {
        isSearch = search;
    }

    public boolean readInforSettingPopupSMS(String key, boolean valueDefault) {
        SharedPreferences preferences = getSharedPreferences(Conts.FILENAME_SETTING, 1);
        return preferences.getBoolean(key, valueDefault);
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
}
