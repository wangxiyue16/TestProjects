package com.example.an.testdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.an.testdemo.itemDecorationType.Activity.SimpleDecorationActivity;
import com.example.an.testdemo.itemtype.Activity.itemTypeActivity;
import com.example.an.testdemo.refresh.Activity.refreshActivity;

/**
 * 首页
 */
public class HomeActivity extends AppCompatActivity {
    private TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findView();
        setOnClick();
    }

    public void findView() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
    }

    public void setOnClick() {
        tv1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //分隔线样式
                startActivity(new Intent(getApplication(), SimpleDecorationActivity.class));
            }
        });

        tv2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //item不同样式
                startActivity(new Intent(getApplication(), itemTypeActivity.class));
            }
        });

        tv3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //刷新演示
                startActivity(new Intent(getApplication(), refreshActivity.class));
            }
        });
    }
}
