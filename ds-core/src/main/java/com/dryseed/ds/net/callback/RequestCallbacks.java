package com.dryseed.ds.net.callback;

import android.os.Handler;

import com.dryseed.ds.ui.loader.DsLoader;
import com.dryseed.ds.ui.loader.LoaderStyle;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 2017/10/21.
 */
public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;

    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest request, ISuccess success, IError error, IFailure failure, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (null != SUCCESS) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (null != ERROR) {
                ERROR.onError(response.code(), response.message());
            }
        }

        if (null != REQUEST) {
            REQUEST.onRequestEnd();
        }

        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (null != FAILURE) {
            FAILURE.onFailure();
        }

        if (null != REQUEST) {
            REQUEST.onRequestEnd();
        }

        stopLoading();
    }

    private void stopLoading(){
        //test : add delay
        if (null != LOADER_STYLE) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    DsLoader.stopLoading();
                }
            }, 1000);
        }
    }
}
