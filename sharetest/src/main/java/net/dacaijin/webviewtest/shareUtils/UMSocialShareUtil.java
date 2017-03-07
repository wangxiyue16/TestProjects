package net.dacaijin.webviewtest.shareUtils;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

public class UMSocialShareUtil {

    private static final String TAG = UMSocialShareUtil.class.getSimpleName();

    /**
     * 分平台进行分享
     *
     * @param media
     * @param context
     * @param title
     * @param content
     * @param targetUrl
     * @param resId     图片资源id
     * @return
     */
    public static boolean postShare(SHARE_MEDIA media, Activity context, String title, String content, String targetUrl, int resId) {
        return postShare(media, context, title, content, targetUrl, new UMImage(context, resId));
    }

    public static boolean postShare(SHARE_MEDIA media, Activity context, String title, String content, String targetUrl, int resId, UMShareListener listener) {
        return postShare(media, context, title, content, targetUrl, new UMImage(context, resId), listener);
    }

    /**
     * 分平台进行分享
     *
     * @param media     平台
     * @param context
     * @param title     标题
     * @param text      内容
     * @param targetUrl 跳转url
     * @param image     图片 不可为null
     */
    public static boolean postShare(SHARE_MEDIA media, Activity context, String title, String text, String targetUrl, UMImage image) {
        postShare(media, context, title, text, targetUrl, image, getUMShareListener());
        return true;
    }

    public static boolean postShare(SHARE_MEDIA media, Activity context, String title, String text, String targetUrl, UMImage image, UMShareListener listener) {
        ShareAction action = new ShareAction(context);
        action.setPlatform(media);

        //设置内容
        if (!TextUtils.isEmpty(text)) {
            action.withText(text);
        }

        //设置标题
        if (!TextUtils.isEmpty(text)) {
            action.withTitle(title);
        }

        action.withTargetUrl(targetUrl);
        action.withMedia(image);
        action.setCallback(listener);
        action.share();

        return true;
    }

    public static UMShareListener getUMShareListener() {
        UMShareListener listener = new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA share_media) {
                Log.d(TAG, "share success");
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                Log.d(TAG, "share error");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                Log.d(TAG, "share cancel");
            }
        };
        return listener;
    }
}
