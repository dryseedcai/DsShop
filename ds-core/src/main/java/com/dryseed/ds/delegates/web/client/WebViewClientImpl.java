package com.dryseed.ds.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dryseed.ds.app.ConfigKeys;
import com.dryseed.ds.app.Ds;
import com.dryseed.ds.delegates.IPageLoadListener;
import com.dryseed.ds.delegates.web.WebDelegate;
import com.dryseed.ds.delegates.web.route.Router;
import com.dryseed.ds.ui.loader.DsLoader;
import com.dryseed.ds.util.log.DsLogger;
import com.dryseed.ds.util.storage.DsPreference;

/**
 * Created by caiminming on 2017/10/27.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Ds.getHandler();

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        DsLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        DsLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        syncCookie(); //同步cookie

        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                DsLoader.stopLoading();
            }
        }, 1000);
    }


    /**
     * 同步cookie，配合AddCookieInterceptor拦截器
     */
    private void syncCookie() {
        final CookieManager cookieManager = CookieManager.getInstance();
        /*
            注意：这里的cookie和api请求的cookie是不一样的，这个在网页不可见
         */
        final String webHost = Ds.getConfiguration(ConfigKeys.WEB_HOST);
        if (cookieManager.hasCookies() && !TextUtils.isEmpty(webHost)) {
            final String cookieStr = cookieManager.getCookie(webHost);
            if (!TextUtils.isEmpty(cookieStr)) {
                DsPreference.addCustomAppProfile("cookie", cookieStr);
            }
        }
    }
}
