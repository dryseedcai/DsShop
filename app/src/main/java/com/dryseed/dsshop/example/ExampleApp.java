package com.dryseed.dsshop.example;

import android.app.Application;

import com.dryseed.ds.app.Ds;
import com.dryseed.ds.net.interceptors.AddCookieInterceptor;
import com.dryseed.ds.net.interceptors.DebugInterceptor;
import com.dryseed.ds.util.dimen.DPIUtil;
import com.dryseed.dsshop.database.DatabaseManager;
import com.dryseed.ds.debug.RequestData;
import com.dryseed.dsshop.example.event.ShareEvent;
import com.dryseed.dsshop.example.event.TestEvent;
import com.dryseed.dsshop.icon.FontDsModule;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by User on 2017/10/21.
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Ds.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontDsModule())
                .withInterceptor(new DebugInterceptor(RequestData.TEST.name(), R.raw.test))
                .withInterceptor(new DebugInterceptor(RequestData.USER_PROFILE.name(), R.raw.user_profile))
                .withInterceptor(new DebugInterceptor(RequestData.INDEX_DATA.name(), R.raw.index_data))
                .withInterceptor(new DebugInterceptor(RequestData.SORT_LIST.name(), R.raw.sort_list))
                .withInterceptor(new DebugInterceptor(RequestData.SORT_CONTENT_DATA.name(), R.raw.sort_content_data_1))
                .withInterceptor(new DebugInterceptor(RequestData.SHOP_CART_DATA.name(), R.raw.shop_cart_data))
                .withInterceptor(new DebugInterceptor(RequestData.SHOP_CART_DATA.name(), R.raw.shop_cart_data))
                .withInterceptor(new DebugInterceptor(RequestData.ORDER_LIST.name(), R.raw.order_list))
                .withInterceptor(new DebugInterceptor(RequestData.UPLOAD_IMG.name(), R.raw.upload_img))
                .withInterceptor(new DebugInterceptor(RequestData.ADDRESS.name(), R.raw.address))
                .withInterceptor(new AddCookieInterceptor()) //cookie同步拦截器
                .withWeChatAppId("你的微信AppKey")
                .withWeChatAppSecret("你的微信AppSecret")
                .withJavascriptInterface("ds")
                .withWebEvent("test", new TestEvent())
                .withWebEvent("share", new ShareEvent())
                .withWebHost("https://www.baidu.com/")
                .configure();

        try {
            DPIUtil.setDensity(getResources().getDisplayMetrics().density);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        DatabaseManager.getInstance().init(this);

        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
