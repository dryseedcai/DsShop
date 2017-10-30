package com.dryseed.dsshop.pay;

/**
 * Created by caiminming on 2017/10/30.
 */

public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
