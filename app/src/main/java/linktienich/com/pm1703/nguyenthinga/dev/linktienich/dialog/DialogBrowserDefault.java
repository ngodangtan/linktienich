package linktienich.com.pm1703.nguyenthinga.dev.linktienich.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.Conts;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.R;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.activity.MainActivity;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.activity.SearchActivity;

/**
 * Created by Hoang on 7/2/2016.
 */
public class DialogBrowserDefault extends Dialog implements View.OnClickListener {
    private Context context;
    private String url;
    private MainActivity mainActivity;
    private SearchActivity searchActivity;

    private LinearLayout llBrowserCurrent, llBrowserDefault;
    private CheckBox cbxSaveDefaultBrowser;

    private boolean isSearch;

    public DialogBrowserDefault(Context context, boolean isSearch) {
        super(context);
        this.context = context;
        this.isSearch = isSearch;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_select_browser);
        initView();
    }

    private void initView() {
        llBrowserCurrent = (LinearLayout) findViewById(R.id.ll_browser_current);
        llBrowserDefault = (LinearLayout) findViewById(R.id.ll_browser_default);
        llBrowserCurrent.setOnClickListener(this);
        llBrowserDefault.setOnClickListener(this);

        cbxSaveDefaultBrowser = (CheckBox) findViewById(R.id.cbx_save_default_browser);
        cbxSaveDefaultBrowser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                writeSetting(Conts.KEY_SETTING_SAVE_DEFAULT_BROWSER, isChecked);
            }
        });
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_browser_current:
                if(isSearch){
                    searchActivity.showWebViewActivity(getUrl());
                }else {
                    mainActivity.showWebViewActivity(getUrl());
                }
                writeSetting(Conts.KEY_BROWSER_CURRENT, true);
                writeSetting(Conts.KEY_BROWSER_DEFAULT, false);
                dismiss();
                break;
            case R.id.ll_browser_default:
                if(isSearch){
                    searchActivity.goToBrowserDefault(getUrl());
                }else {
                    mainActivity.goToBrowserDefault(getUrl());
                }
                writeSetting(Conts.KEY_BROWSER_CURRENT, false);
                writeSetting(Conts.KEY_BROWSER_DEFAULT, true);
                dismiss();
                break;

        }
    }

    public void writeSetting(String key, boolean value) {
        SharedPreferences pref = context.getSharedPreferences(Conts.FILENAME_SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean readInforSettingPopupSMS(String key, boolean valueDefault) {
        SharedPreferences preferences = context.getSharedPreferences(Conts.FILENAME_SETTING, 1);
        return preferences.getBoolean(key, valueDefault);
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setSearchActivity(SearchActivity searchActivity) {
        this.searchActivity = searchActivity;
    }
}
