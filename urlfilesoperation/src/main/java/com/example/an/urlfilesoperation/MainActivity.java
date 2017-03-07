package com.example.an.urlfilesoperation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.an.urlfilesoperation.Activity.WebActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        findView();
        setView();
    }

    public void initData(){

    }

    public void findView(){
    }

    public void setView(){
        setOnClickListener(R.id.tv_click);
    }

    /**
     * 设置点击事件监听
     *
     * @param resId view的id
     */
    protected void setOnClickListener(@IdRes int resId) {
        View v = findViewById(resId);
        if (v != null) {
            v.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_click:
                Intent intent = new Intent(App.ct(), WebActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(BaseExtra.KIsLoadJSTitle, true);
//                intent.putExtra(Extra.KUrl, url);
                App.ct().startActivity(intent);
                break;
            default:
                break;
        }
    }
}
