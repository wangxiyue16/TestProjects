package net.dacaijin.webviewtest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import net.dacaijin.webviewtest.R;
 
public class WelcomeActivity extends Activity {
    private ImageView back_img;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        // 设置下次打开不跳转
        back_img = (ImageView) findViewById(R.id.back_img);
        back_img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(WelcomeActivity.this, WebActivity.class);
				startActivity(intent);
				finish();
			}
		});
    }
 
}