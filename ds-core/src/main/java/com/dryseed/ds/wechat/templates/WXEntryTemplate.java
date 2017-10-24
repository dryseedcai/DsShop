package com.dryseed.ds.wechat.templates;


import com.dryseed.ds.wechat.BaseWXEntryActivity;
import com.dryseed.ds.wechat.DsWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        DsWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}

