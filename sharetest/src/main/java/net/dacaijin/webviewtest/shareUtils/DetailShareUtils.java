package net.dacaijin.webviewtest.shareUtils;

import android.app.Activity;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import net.dacaijin.webviewtest.R;

/**
 * description:
 * author: WDSG
 * date: 2017/2/7
 */
public class DetailShareUtils {

    public static void shareToWx(Activity activity, String title, String content, String url, String ShareImage, UMShareListener UMShareListener) {
        UMSocialShareUtil.postShare(SHARE_MEDIA.WEIXIN, // 平台
                activity,
                title,
                content,
                url,
                R.drawable.logo,
                UMShareListener);
    }

    public static void shareToQQ(Activity activity, String title, String content, String url, String ShareImage, UMShareListener UMShareListener) {
        UMSocialShareUtil.postShare(SHARE_MEDIA.QQ, // 平台
                activity,
                title,
                content,
                url,
                R.drawable.logo,
                UMShareListener);
    }

    public static void shareToSina(Activity activity, String title, String content, String url, String ShareImage, UMShareListener UMShareListener) {
        UMSocialShareUtil.postShare(SHARE_MEDIA.SINA, // 平台
                activity,
                title,
                content,
                url,
                R.drawable.logo,
                UMShareListener);
    }

    public static void shareToWxFriend(Activity activity, String title, String content, String url, String ShareImage, UMShareListener UMShareListener) {
        UMSocialShareUtil.postShare(SHARE_MEDIA.WEIXIN_CIRCLE, // 平台
                activity,
                title,
                content,
                url,
                R.drawable.logo,
                UMShareListener);
    }

}
