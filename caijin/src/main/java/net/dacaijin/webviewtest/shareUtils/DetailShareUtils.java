package net.dacaijin.webviewtest.shareUtils;

import android.app.Activity;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

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
                new UMImage(activity, ShareImage),
                UMShareListener);
    }

    public static void shareToQQ(Activity activity, String title, String content, String url, String ShareImage, UMShareListener UMShareListener) {
        UMSocialShareUtil.postShare(SHARE_MEDIA.QQ, // 平台
                activity,
                title,
                content,
                url,
                new UMImage(activity, ShareImage),
                UMShareListener);
    }

    public static void shareToSina(Activity activity, String title, String content, String url, String ShareImage, UMShareListener UMShareListener) {
        UMSocialShareUtil.postShare(SHARE_MEDIA.SINA, // 平台
                activity,
                title,
                content,
                url,
                new UMImage(activity, ShareImage),
                UMShareListener);
    }

    public static void shareToWxFriend(Activity activity, String title, String content, String url, String ShareImage, UMShareListener UMShareListener) {
        UMSocialShareUtil.postShare(SHARE_MEDIA.WEIXIN_CIRCLE, // 平台
                activity,
                title,
                content,
                url,
                new UMImage(activity, ShareImage),
                UMShareListener);
    }

}
