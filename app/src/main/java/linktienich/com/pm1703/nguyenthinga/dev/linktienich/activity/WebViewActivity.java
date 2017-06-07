package linktienich.com.pm1703.nguyenthinga.dev.linktienich.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.Conts;
import linktienich.com.pm1703.nguyenthinga.dev.linktienich.R;

public class WebViewActivity extends Activity implements OnClickListener {
	private EditText edtUrl;
	private WebView wvNews;
	private View viewPageLoading;
	private ImageView imvReload;

	private static final int TIME_INTERVAL = 2000;
	private static final String TAG = "MainActivity";
	private Handler mHandler = new Handler();
	private boolean doubleBackToExitPressedOnce;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_activity);
		initView();
		getData();
		wvNews.loadUrl(edtUrl.getText().toString());
	}

	private void getData() {
		Intent intent=getIntent();
		edtUrl.setText(intent.getStringExtra(Conts.KEY_URL));
	}

	private void initView() {
		edtUrl=(EditText)findViewById(R.id.edt_url);
		wvNews=(WebView)findViewById(R.id.wv_news);
		wvNews.setWebViewClient(webViewClient);
		viewPageLoading=(View)findViewById(R.id.page_loading);
		imvReload=(ImageView)findViewById(R.id.imv_reload);
		imvReload.setOnClickListener(this);
		
	}
	private WebViewClient webViewClient =new WebViewClient(){

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			viewPageLoading.setVisibility(View.VISIBLE);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			viewPageLoading.setVisibility(View.GONE);
		}
		
	};
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imv_reload:
			Toast.makeText(this, edtUrl.getText().toString(), Toast.LENGTH_SHORT).show();
			wvNews.loadUrl(edtUrl.getText().toString());
			break;

		default:
			break;
		}
	}
	private final Runnable mRunnable = new Runnable() {
		@Override
		public void run() {
			doubleBackToExitPressedOnce = false;
		}
	};

	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
			return;
		}

		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();

		mHandler.postDelayed(mRunnable, TIME_INTERVAL);
	}
}
