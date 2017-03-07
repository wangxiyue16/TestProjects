package com.example.an.retrofittest.Activity;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.Window;

import com.example.an.retrofittest.interfaces.IInitialize;
import com.example.an.retrofittest.interfaces.INetwork;

/**
 * description:
 * author: WDSG
 * date: 2017/3/6
 */
public abstract class BaseActivity extends Activity implements IInitialize, INetwork {

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFormat(PixelFormat.TRANSPARENT);
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
//        mDecorView = new DecorViewEx(this, getTitleBarState(), getInitRefreshWay(), initLoadingDialog());
//        mDecorView.setContentView(layoutResID, getContentHeaderViewId());
//        autoFitAll(mDecorView);
//
//        super.setContentView(mDecorView);
        init();
    }

    private void init() {
        initData();
        initTitleBar();
        findViews();
        setViewsValue();
    }
}
