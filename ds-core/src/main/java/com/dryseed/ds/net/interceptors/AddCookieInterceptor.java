package com.dryseed.ds.net.interceptors;

import com.dryseed.ds.util.storage.DsPreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by caiminming on 2017/10/27.
 */

public class AddCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(DsPreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String cookie) throws Exception {
                        //给原生api请求附带上webview拦截下来的cookie
                        builder.addHeader("cookie", cookie);
                    }
                });

        return chain.proceed(builder.build());
    }
}
