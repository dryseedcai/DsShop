package com.dryseed.ds.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 2017/10/21.
 */
public class RequestCallbacks implements Callback<String>{
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    public RequestCallbacks(IRequest request, ISuccess success, IError error, IFailure failure) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(null != SUCCESS){
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if(null != ERROR){
                ERROR.onError(response.code(), response.message());
            }
        }

        if(null != REQUEST){
            REQUEST.onRequestEnd();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(null != FAILURE){
            FAILURE.onFailure();
        }

        if(null != REQUEST){
            REQUEST.onRequestEnd();
        }
    }
}
