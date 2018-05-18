package com.allen.res.collect.webview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.allen.res.collect.R;

/**
 * WebView使用
 */
public class WebViewActivity extends Activity {

    TextView beginLoading, endLoading, loading, mtitle;

    private WebView mWebView;
    private WebSettings mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebView = findViewById(R.id.webView1);
        beginLoading = findViewById(R.id.text_beginLoading);
        endLoading = findViewById(R.id.text_endLoading);
        loading = findViewById(R.id.text_Loading);
        mtitle = findViewById(R.id.title);

        setupSetting();

        mWebView.loadUrl("http://www.baidu.com/");
//        mWebView.loadUrl("http://61.28.113.182:2082/webApp/dist/index.html#/business/signManagement/familyManagement/qygl?doctor_id=11111006&doctor_group_id=53&hospital_id=2000001");
    }

    private void setupSetting() {
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        mSettings = mWebView.getSettings();
        // WebView是否支持使用屏幕上的缩放控件和手势进行缩放，默认值true
        mSettings.setSupportZoom(true);
        // 是否允许WebView度超出以概览的方式载入页面，默认false
        mSettings.setLoadWithOverviewMode(true);
        // WebView是否支持HTML的“viewport”标签或者使用wide viewport
        mSettings.setUseWideViewPort(true);
        // 设置默认的字符编码集，默认”UTF-8”
        mSettings.setDefaultTextEncodingName("UTF-8");
        // WebView是否下载图片资源，默认为true
        mSettings.setLoadsImagesAutomatically(true);
        // 设置布局，会引起WebView的重新布局（relayout）,默认值NARROW_COLUMNS
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        // 先阻塞加载图片
        mSettings.setBlockNetworkImage(true);

        // 设置WebView是否允许执行JavaScript脚本，默认false，不允许
        mSettings.setJavaScriptEnabled(true);
        // 让JavaScript自动打开窗口，默认false
        mSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 是否允许访问文件，默认允许
        mSettings.setAllowFileAccess(true);
        // 数据库存储API是否可用，默认值false
        mSettings.setDatabaseEnabled(true);
        // DOM存储API是否可用，默认false
        mSettings.setDomStorageEnabled(true);
        // 应用缓存API是否可用，默认值false, 结合setAppCachePath(String)使用。
        mSettings.setAppCacheEnabled(true);
        // 设置应用缓存文件的路径
//        settings.setAppCachePath("");

        // 重写使用缓存的方式，默认值LOAD_DEFAULT
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            mSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            mSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        // SDK >= 16,是否允许运行在一个URL环境（the context of a file scheme URL）中的JavaScript访问来自其他URL环境的内容，为了保证安全，应该不允许
        mSettings.setAllowFileAccessFromFileURLs(true);
        // SDK >= 16,是否允许运行在一个file schema URL环境下的JavaScript访问来自其他任何来源的内容，包括其他file schema URLs.
        mSettings.setAllowUniversalAccessFromFileURLs(true);

        // SDK >= 12,是否允许在WebView中访问内容URL（Content Url），默认允许。内容Url访问允许WebView从安装在系统中的内容提供者载入内容。
        mSettings.setAllowContentAccess(true);

        mWebView.setWebViewClient(webViewClient);
        mWebView.setWebChromeClient(webChromeClient);
    }

    WebViewClient webViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
            return false;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            System.out.println("开始加载了");
            beginLoading.setText("开始加载了");
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mSettings.setBlockNetworkImage(false);
            if (!mSettings.getLoadsImagesAutomatically()) {
                mSettings.setLoadsImagesAutomatically(true);
            }
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            endLoading.setText("结束加载了");
            super.onPageCommitVisible(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }
    };

    WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress < 100) {
                String progress = newProgress + "%";
                loading.setText(progress);
            } else if (newProgress == 100) {
                String progress = newProgress + "%";
                loading.setText(progress);
            }
//            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            System.out.println("标题在这里");
            mtitle.setText(title);
//            super.onReceivedTitle(view, title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }
    };

    public void btnCloseAc(View view) {
        this.finish();
    }

    /**
     * 点击返回上一页面而不是退出浏览器
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * 销毁WebView
     */
    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.stopLoading();
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
        System.exit(0);
    }
}
