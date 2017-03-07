package com.example.an.urlfilesoperation.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * description: 文件上传的服务
 * author: WDSG
 * date: 2016/10/19
 */
public class UpLoadService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 文件上传方法
     */
    public void upLoadFile(){

    }
}
