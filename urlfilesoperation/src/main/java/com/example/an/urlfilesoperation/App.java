package com.example.an.urlfilesoperation;

import android.app.Application;
import android.content.Context;

/**
 * description:
 * author: WDSG
 * date: 2016/10/17
 */
public class App extends Application {
    protected static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    /**
     * 返回getApplicationContext()
     *
     * @return sContext
     */
    public static Context ct() {
        return sContext;
    }
}
