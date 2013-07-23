package com.deepin.traveltimes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.baidu.social.core.BaiduSocialException;
import com.baidu.social.core.BaiduSocialListener;
import com.baidu.social.core.Utility;
import com.baidu.sociallogin.BaiduSocialLogin;
import com.deepin.traveltimes.social.SocialShareConfig;

public class LoginActivity extends Activity {

	final Handler handler = new Handler(Looper.getMainLooper());

	public static String TAG = "LoginActivity";
	public static String FLAG_FILE_NAME = "LoginActivity";

	private BaiduSocialLogin socialLogin;
	private final static String baidu_appKey = SocialShareConfig.BaiduApiKey;

	private ImageButton sinaWeibo;
	private ImageButton qqdenglu;
	private ImageButton weixin;
	private ImageButton baidu;

	private void writeFlag() throws IOException {

	}

	private boolean checkFlag() {
		try {
			FileInputStream fos = openFileInput(FLAG_FILE_NAME);
			fos.close();
			return true;
		} catch (IOException e) {
			try {
				FileOutputStream fos = openFileOutput(FLAG_FILE_NAME,
						Context.MODE_PRIVATE);
				fos.write(1);
				fos.close();
			} catch (IOException e1) {

			}
			return false;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

		setContentView(R.layout.activity_login);

		// 实例化baidu社会化登录，传入appkey
		socialLogin = BaiduSocialLogin.getInstance(this, baidu_appKey);

		// 设置支持腾讯微博单点登录的appid
		socialLogin.supportQQSso(SocialShareConfig.QQ_SSO_APP_KEY);

		// 设置支持新浪微博单点登录的appid
		socialLogin.supportWeiBoSso(SocialShareConfig.SINA_SSO_APP_KEY);

		sinaWeibo = (ImageButton) findViewById(R.id.imageButtonWeibo);
		qqdenglu = (ImageButton) findViewById(R.id.imageButtonQQ);
		weixin = (ImageButton) findViewById(R.id.imageButtonWeixin);
		baidu = (ImageButton) findViewById(R.id.imageButtonBaidu);

		baidu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (socialLogin.isAccessTokenValid(Utility.SHARE_TYPE_BAIDU)) {
					socialLogin.getUserInfoWithShareType(LoginActivity.this,
							Utility.SHARE_TYPE_BAIDU, new UserInfoListener());
				} else
					socialLogin.authorize(LoginActivity.this,
							Utility.SHARE_TYPE_BAIDU, new UserInfoListener());

			}
		});
	}

	class UserInfoListener implements BaiduSocialListener {

		@Override
		public void onAuthComplete(Bundle values) {
			// TODO Auto-generated method stubis
			Log.e(TAG, "onAuthComplete: " + values.toString());

		}

		@Override
		public void onApiComplete(String responses) {
			// TODO Auto-generated method stub

			final String responseStr = responses;

			handler.post(new Runnable() {
				@Override
				public void run() {
					if (checkFlag()) {
						Log.e(TAG, Utility.decodeUnicode(responseStr));
						Intent i = new Intent(LoginActivity.this,
								TimesLine.class);
						LoginActivity.this.startActivity(i);
						LoginActivity.this.finish();
					}
				}
			});
		}

		@Override
		public void onError(BaiduSocialException e) {
			final String error = e.toString();
			handler.post(new Runnable() {
				@Override
				public void run() {
					// info.setText(error);
					Log.e(TAG, error);
				}
			});
		}
	}
}
