package linktienich.com.pm1703.nguyenthinga.dev.linktienich.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.R;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.activity.MainActivity;

/**
 * Created by Hoang on 7/7/2016.
 */
public class DialogOpenNetwork extends Dialog implements View.OnClickListener {

    private Button btnWifj, btnNetwork, btnCancel;

    private Context context;
    private MainActivity mainActivity;

    public DialogOpenNetwork(Context context, MainActivity mainActivity) {
        super(context);
        this.mainActivity = mainActivity;
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_network_not_found);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        btnNetwork = (Button) findViewById(R.id.btn_open_setting_network);
        btnNetwork.setOnClickListener(this);
        btnWifj = (Button) findViewById(R.id.btn_open_setting_wifi);
        btnWifj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                dismiss();
                mainActivity.finish();
                break;
            case R.id.btn_open_setting_network:
                Intent intentNetwork = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                context.startActivity(intentNetwork);
                dismiss();
                break;
            case R.id.btn_open_setting_wifi:
                Intent intentWifj = new Intent(Settings.ACTION_WIFI_SETTINGS);
                context.startActivity(intentWifj);
                dismiss();
                break;
        }
    }
}
