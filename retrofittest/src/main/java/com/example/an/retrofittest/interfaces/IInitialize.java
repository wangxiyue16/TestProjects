package com.example.an.retrofittest.interfaces;

import android.support.annotation.LayoutRes;

/**
 * description:
 * author: WDSG
 * date: 2017/3/6
 */
public interface IInitialize {
    @LayoutRes
    int getContentViewId();

    void initTitleBar();

    //初始化数据
    void initData();

    //查找viewid
    void findViews();

    //设置参数
    void setViewsValue();
}
