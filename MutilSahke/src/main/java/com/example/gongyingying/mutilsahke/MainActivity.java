package com.example.gongyingying.mutilsahke;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements OnClickListener, OnLongClickListener {

    private ShakeView shakeView;
    private ShakeView shakeView1;
    private ShakeView shakeView2;
    private ShakeView shakeView3;
    private Timer timer;
    private TimerTask task;
    private Handler handler;
    private TextView text;
    private long count;
    private LinearLayout layout;
    private Animation scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout) findViewById(R.id.layout);


//        shakeView = (ShakeView) findViewById(R.id.shake);
//        shakeView1 = (ShakeView) findViewById(R.id.shake1);
//        shakeView2 = (ShakeView) findViewById(R.id.shake2);
//        shakeView3 = (ShakeView) findViewById(R.id.shake3);

        shakeView = new ShakeView(this);

        shakeView1 = new ShakeView(this);
        shakeView2 = new ShakeView(this);
        shakeView3 = new ShakeView(this);

        layout.addView(shakeView, new LinearLayout.LayoutParams(100, 100));
        layout.addView(shakeView1, new LinearLayout.LayoutParams(100, 100));
        layout.addView(shakeView2, new LinearLayout.LayoutParams(100, 100));
        layout.addView(shakeView3, new LinearLayout.LayoutParams(100, 100));

        shakeView.setOnClickListener(this);
        shakeView1.setOnClickListener(this);
        shakeView2.setOnClickListener(this);
        shakeView3.setOnClickListener(this);

        shakeView.setOnLongClickListener(this);
        shakeView1.setOnLongClickListener(this);
        shakeView2.setOnLongClickListener(this);
        shakeView3.setOnLongClickListener(this);


        scaleAnimation = new ScaleAnimation(0.1f, 1.0f,0.1f,1.0f);
        scaleAnimation.setDuration(500);

    }

    @Override
    public void onClick(View v) {
        if (v == shakeView) {
//            shakeView.stopShake();
//            shakeView.setAnimation(scaleAnimation);
            shakeView.startZoom();
//            layout.removeView(shakeView);

        } else if (v == shakeView1) {

            shakeView1.startZoom();
//            layout.removeView(shakeView1);
        } else if (v == shakeView2) {
            shakeView2.startZoom();
//            layout.removeView(shakeView2);

        } else if (v == shakeView3) {
            shakeView3.startZoom();
//            layout.removeView(shakeView3);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        shakeView.shakeShake();
        shakeView1.shakeShake();
        shakeView2.shakeShake();
        shakeView3.shakeShake();
        return true;
    }
}
