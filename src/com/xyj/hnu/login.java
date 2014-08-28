package com.xyj.hnu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xyj.hnu.app.AppContext;
import com.xyj.hnu.fragment.MainFragment;
import com.xyj.hnu.tools.Metrics;

public class login extends Activity {
	private int win_width;
	private EditText ed_user, ed_pswd;
	private Button btn_login;
	private TextView tv_scan;
	private ProgressDialog dialog;
	private Editor editor;
	private String user_xh;
	private String passwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		// 此时，欢迎界面以后不用显示
		saveConfig();
		win_width = Metrics.getWidthPixels();
		ed_user = (EditText) findViewById(R.id.ed_user);
		ed_pswd = (EditText) findViewById(R.id.et_pwd);
		btn_login = (Button) findViewById(R.id.btn_login);
		tv_scan = (TextView) findViewById(R.id.tv_scan);
		adjust();

		tv_scan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(login.this, MainFragment.class);
				startActivity(intent);
				login.this.finish();
			}
		});

		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AppContext appContext=(AppContext)getApplication();
				user_xh = ed_user.getText().toString().trim();
				passwd = ed_pswd.getText().toString().trim();
				if(TextUtils.isEmpty(user_xh)||TextUtils.isEmpty(passwd)){
					Toast.makeText(login.this, "请填写学号和密码", 0).show();
				}else if (appContext.isNetworkConnected()) {
					showLoading();
//					LoginThread thread = new LoginThread(user_xh, passwd,
//							handler);
//					thread.start();
				}else{
					Toast.makeText(login.this, "网络连接不可用", 0).show();
				}
			}
		});
	}

	private void saveConfig() {
		SharedPreferences preferences = getSharedPreferences("config",
				MODE_PRIVATE);
		editor = preferences.edit();
		editor.putBoolean("isWelcomed", true);
		editor.commit();
	}

	private void showLoading() {
		dialog = new ProgressDialog(this);
		dialog.setMessage("正在登录");
		dialog.setCancelable(false);
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		dialog.show();
	}

	// 调整ui
	private void adjust() {
		int width = win_width * 2 / 3;
		int height = width / 5;
		ed_user.setWidth(width);
		ed_user.setHeight(height);
		ed_pswd.setWidth(width);
		ed_pswd.setHeight(height);
	}

}
