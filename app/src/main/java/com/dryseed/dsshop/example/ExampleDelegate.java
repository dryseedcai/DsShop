package com.dryseed.dsshop.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.dryseed.ds.delegates.DsDelegate;
import com.dryseed.ds.net.RestClient;
import com.dryseed.ds.net.callback.IError;
import com.dryseed.ds.net.callback.IFailure;
import com.dryseed.ds.net.callback.ISuccess;

import java.util.HashMap;

/**
 * Created by User on 2017/10/21.
 */
public class ExampleDelegate extends DsDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "onFailure", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(), "onError", Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }

}
