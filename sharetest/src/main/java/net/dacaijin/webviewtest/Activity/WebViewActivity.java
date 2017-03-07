package net.dacaijin.webviewtest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import net.dacaijin.webviewtest.R;
import net.dacaijin.webviewtest.shareUtils.DetailShareUtils;

/**
 * description:
 * author: WDSG
 * date: 2017/2/7
 */
public class WebViewActivity extends Activity {
    private WebView mWebView;
    private WebSettings mWebSettings;
    private final String mUrl = "http://news.eechou.com/";
    private UMShareListener mUmShareListener;

    private String mTitle;
    private String mShareUrl;
    private String mShareImage;
    private String mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        initData();
        findView();
        setViewValues();
    }

    public void initData() {
        mTitle = "分享测试";
        mContent = "这就是测试啊!!!";
        mShareUrl = "http://mobile.umeng.com/social";
        mShareImage = "https://mobile.umeng.com/images/pic/home/social/img-1.png";

    }

    public void findView() {
        mWebView = (WebView) findViewById(R.id.webView);
    }

    public void setViewValues() {

        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        //WebView加载web资源
        mWebSettings.setUserAgentString(mWebSettings.getUserAgentString() + "cn.efunding.app");
        mWebView.loadUrl(mUrl);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(getWebViewClient());
        mWebView.addJavascriptInterface(new HtmlCode(), "code");

        mUmShareListener = new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA platform) {
//                finish();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
//                finish();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
//                finish();
            }
        };
    }

    /**
     * 退出时开启上传服务
     */
    @Override
    public void finish() {
        super.finish();
    }

    public WebViewClient getWebViewClient() {
        return new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                if (url.contains("type") && url.contains("type")) {
                    mTitle = Uri.parse(url).getQueryParameter("title");
                    mContent = Uri.parse(url).getQueryParameter("content");
                    mShareUrl = Uri.parse(url).getQueryParameter("shareurl");
                    mShareImage = Uri.parse(url).getQueryParameter("pic");

                    System.out.println("mTitle   "+mTitle);
                    System.out.println("mContent   "+mContent);
                    System.out.println("mShareUrl   "+mShareUrl);
                    System.out.println("mShareImage   "+mShareImage);
                    if (Uri.parse(url).getQueryParameter("type").equals("qzone")) {
                        DetailShareUtils.shareToQQ(WebViewActivity.this, mTitle, mContent, mShareUrl, mShareImage, getUmShareListener());
                    } else if (Uri.parse(url).getQueryParameter("type").equals("tsina")) {
                        DetailShareUtils.shareToSina(WebViewActivity.this, mTitle, mContent, mShareUrl, mShareImage, getUmShareListener());
                    } else if (Uri.parse(url).getQueryParameter("type").equals("weixin")) {
                        DetailShareUtils.shareToWx(WebViewActivity.this, mTitle, mContent, mShareUrl, mShareImage, getUmShareListener());
                    }
                } else {
                    view.loadUrl(url);
                }
                return true;

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        };

    }

    class HtmlCode {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    public UMShareListener getUmShareListener() {
        return mUmShareListener;
    }

}
