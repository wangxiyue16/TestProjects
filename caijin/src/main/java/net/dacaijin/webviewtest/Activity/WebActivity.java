package net.dacaijin.webviewtest.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import net.dacaijin.webviewtest.R;
import net.dacaijin.webviewtest.shareUtils.DetailShareUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class WebActivity extends Activity {
    private WebView mWebview;
    private ImageView mBackImg;
    private long mExitTime;
    private WebSettings mWebSettings;

//    private final String mUrl = "http://news.eechou.com/";
    private final String mUrl = "http://www.dacaijin.cn/";

    private UMShareListener mUmShareListener;

    private String mTitle;
    private String mShareUrl;
    private String mShareImage;
    private String mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

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
        mWebview = (WebView) findViewById(R.id.webview);
        mBackImg = (ImageView) findViewById(R.id.back_img);
    }

    public void setViewValues() {
        mBackImg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mWebview.goBack();
            }
        });

        mWebSettings = mWebview.getSettings();
        mWebSettings.setDatabaseEnabled(true);
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();

        //启用地理定位
        mWebSettings.setGeolocationEnabled(true);
        //设置定位的数据库路径
        mWebSettings.setGeolocationDatabasePath(dir);

        //最重要的方法，一定要设置，这就是出不来的主要原因
        mWebSettings.setDomStorageEnabled(true);

        // 设置WebView属性，能够执行Javascript脚本
        mWebview.getSettings().setJavaScriptEnabled(true);

        mWebview.addJavascriptInterface(new HtmlCode(), "code");

        // 加载需要显示的网页
        mWebSettings.setUserAgentString(mWebSettings.getUserAgentString() + "cn.efunding.app");
        mWebview.loadUrl(mUrl);

        // 设置Web视图
        mWebview.setWebViewClient(getWebViewClient());
        /*webview.setWebChromeClient(new WebChromeClient(){
            //配置权限（同样在WebChromeClient中实现）
			@Override
			public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
				// TODO Auto-generated method stub
				callback.invoke(origin, true, false);
				super.onGeolocationPermissionsShowPrompt(origin, callback);
			}
		});*/

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

    class HtmlCode {

    }

    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebview.canGoBack()) {
            mWebview.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - mExitTime) > 2000) // System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Web视图
    public WebViewClient getWebViewClient() {
        return new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                System.out.println("url1   "+url);
                if (url.contains("type") && url.contains("shareurl")) {
                    url = url.replaceAll("%(?![0-9a-fA-F]{2})", "%25"); //去掉转码中多余的%
                    System.out.println("url2   "+url);
                    try {
                        url = URLDecoder.decode(url, "utf-8"); //把url转成utf-8格式,防止中文乱码
                        System.out.println("url3   "+url);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

//                    try {
//                        int index = url.indexOf("?");
//                        //拿到?后面的参数,即json对象
//                        String remaining = url.substring(index + 1);
//                        JSONObject object = new JSONObject(url);
//                        //拿到以detail开头的json对象
//                        JSONObject object1 = object.optJSONObject("detail");
//                        //拿到json中title的value
//                        String title = object1.optString("title");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                    int index = url.indexOf("?");
                    String remaining = url.substring(index + 1);
                    String[] parameters = remaining.split("&");
                    Map<String, String> map = new HashMap<>();
                    for (String item : parameters) {
                        int position = item.indexOf("=");
                        String key = item.substring(0, position);
                        String value = item.substring(position + 1);
                        map.put(key, value);
                    }

                    mTitle = map.get("title");
                    mContent = map.get("content");
                    mShareUrl = map.get("shareurl");
                    mShareImage = map.get("pic");
                    System.out.println("pic   "+mShareImage);

                    if (Uri.parse(url).getQueryParameter("type").equals("qzone")) {
                        DetailShareUtils.shareToQQ(WebActivity.this, mTitle, mContent, mShareUrl, mShareImage, getUmShareListener());
                    } else if (Uri.parse(url).getQueryParameter("type").equals("tsina")) {
                        DetailShareUtils.shareToSina(WebActivity.this, mTitle, mContent, mShareUrl, mShareImage, getUmShareListener());
                    } else if (Uri.parse(url).getQueryParameter("type").equals("weixin")) {
                        DetailShareUtils.shareToWx(WebActivity.this, mTitle, mContent, mShareUrl, mShareImage, getUmShareListener());
                    } else if (Uri.parse(url).getQueryParameter("type").equals("wxfriend")) {
                        DetailShareUtils.shareToWxFriend(WebActivity.this, mTitle, mContent, mShareUrl, mShareImage, getUmShareListener());
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