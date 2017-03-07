package net.dacaijin.webviewtest;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import net.dacaijin.webviewtest.Const.ShareParams;

/**
 * description:
 * author: WDSG
 * date: 2017/2/7
 */
public class App extends Application {
    protected static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        setParams();

        sContext = getApplicationContext();
    }

    public void setParams(){
        PlatformConfig.setWeixin(ShareParams.KWxAppId, ShareParams.KWxAppSecret);
        PlatformConfig.setSinaWeibo(ShareParams.KWeiboAppKey, ShareParams.KWeiboAppSecret);
        PlatformConfig.setQQZone(ShareParams.KQQAppId, ShareParams.KQQAppKey);
        Config.DEBUG = true;

        UMShareAPI.get(this);
        Config.REDIRECT_URL = ShareParams.KWeiboCallBack;
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
