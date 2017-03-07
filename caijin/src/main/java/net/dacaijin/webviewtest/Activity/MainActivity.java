package net.dacaijin.webviewtest.Activity;

import android.os.Bundle;
import android.view.Window;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivity extends Activity {
	SharedPreferences showWelcommSp;
	private long mExitTime;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// setContentView(R.layout.activity_main);
		showWelcommSp = getSharedPreferences("showWelcomm", Context.MODE_PRIVATE);
		showWelcomeImg();
	}
	
	private void showWelcomeImg() {
		// 第一次启动
		if (showWelcommSp.getString("show", null) == null) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, WelcomeActivity.class);
			startActivity(intent);
			Editor mimaEt = showWelcommSp.edit();
			mimaEt.putString("show", "1");
			mimaEt.commit();
			finish();
		} else {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, WebActivity.class);
			startActivity(intent);
			finish();
		}
	}

}