package com.example.an.urlfilesoperation.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.an.urlfilesoperation.R;
import com.example.an.urlfilesoperation.Service.UpLoadService;

/**
 * description:
 * author: WDSG
 * date: 2016/10/17
 */
public class WebActivity extends AppCompatActivity {
    private WebView mWebView;
    private WebSettings mWebSettings;
    private final String mUrl = "https://hao.360.cn/?wd_xp1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        initData();
        findView();
        setViewValues();
    }

    public void initData(){
    }

    public void findView(){
        mWebView = (WebView) findViewById(R.id.webView);
    }

    public void setViewValues(){
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        //WebView加载web资源
        mWebView.loadUrl(mUrl);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(getWebViewClient());
        mWebView.addJavascriptInterface(new HtmlCode(), "code");
    }

    public WebViewClient getWebViewClient(){
        return new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
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

    /**
     * 把截取到的内容写入文件
     */
    private void writeContentToFile(){

    }

    /**
     * 退出时开启上传服务
     */
    @Override
    public void finish() {
        startService(new Intent(this, UpLoadService.class));
        super.finish();
    }
}
