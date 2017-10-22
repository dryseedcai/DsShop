package com.dryseed.ds.net.rx;

import android.content.Context;

import com.dryseed.ds.net.RestClient;
import com.dryseed.ds.net.RestCreator;
import com.dryseed.ds.net.callback.IError;
import com.dryseed.ds.net.callback.IFailure;
import com.dryseed.ds.net.callback.IRequest;
import com.dryseed.ds.net.callback.ISuccess;
import com.dryseed.ds.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by User on 2017/10/21.
 */
public class RxRestClientBuilder {

    private static final Map<String, Object> PARAMS = RestCreator.getParams();

    private String mURL = null;
    private RequestBody mBody = null;
    private File mFile = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        mURL = url;
        return this;
    }

    public final RxRestClientBuilder params(Map<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String filePath) {
        mFile = new File(filePath);
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        mContext = context;
        mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        mContext = context;
        mLoaderStyle = loaderStyle;
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(
                mURL,
                PARAMS,
                mBody,
                mFile,
                mContext,
                mLoaderStyle);
    }

}
