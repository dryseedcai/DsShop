package com.dryseed.dsshop.example;

import android.app.Application;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDexApplication;

import com.dryseed.ds.app.Ds;
import com.dryseed.ds.net.interceptors.AddCookieInterceptor;
import com.dryseed.ds.net.interceptors.DebugInterceptor;
import com.dryseed.ds.util.callback.CallbackManager;
import com.dryseed.ds.util.callback.CallbackType;
import com.dryseed.ds.util.callback.IGlobalCallback;
import com.dryseed.ds.util.dimen.DPIUtil;
import com.dryseed.dsshop.database.DatabaseManager;
import com.dryseed.ds.debug.RequestData;
import com.dryseed.dsshop.example.event.ShareEvent;
import com.dryseed.dsshop.example.event.TestEvent;
import com.dryseed.dsshop.icon.FontDsModule;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Interceptor;

/**
 * Created by User on 2017/10/21.
 */
public class ExampleApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Ds.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontDsModule())
                .withInterceptors(initDebugInterceptors())
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

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Ds.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Ds.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Ds.getApplicationContext())) {
                            JPushInterface.stopPush(Ds.getApplicationContext());
                        }
                    }
                });


        initStetho();
    }

    private ArrayList<Interceptor> initDebugInterceptors() {
        ArrayList<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new DebugInterceptor(RequestData.TEST.name(), R.raw.test));
        interceptors.add(new DebugInterceptor(RequestData.USER_PROFILE.name(), R.raw.user_profile));
        interceptors.add(new DebugInterceptor(RequestData.INDEX_DATA.name(), R.raw.index_data));
        interceptors.add(new DebugInterceptor(RequestData.SORT_LIST.name(), R.raw.sort_list));
        interceptors.add(new DebugInterceptor(RequestData.SORT_CONTENT_DATA.name(), R.raw.sort_content_data_1));
        interceptors.add(new DebugInterceptor(RequestData.SHOP_CART_DATA.name(), R.raw.shop_cart_data));
        interceptors.add(new DebugInterceptor(RequestData.SHOP_CART_DATA.name(), R.raw.shop_cart_data));
        interceptors.add(new DebugInterceptor(RequestData.ORDER_LIST.name(), R.raw.order_list));
        interceptors.add(new DebugInterceptor(RequestData.UPLOAD_IMG.name(), R.raw.upload_img));
        interceptors.add(new DebugInterceptor(RequestData.ADDRESS.name(), R.raw.address));
        interceptors.add(new DebugInterceptor(RequestData.ABOUT.name(), R.raw.about));
        interceptors.add(new DebugInterceptor(RequestData.SEARCH.name(), R.raw.search));
        return interceptors;
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

}
