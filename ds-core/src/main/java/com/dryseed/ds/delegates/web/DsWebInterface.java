package com.dryseed.ds.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.dryseed.ds.delegates.web.event.Event;
import com.dryseed.ds.delegates.web.event.EventManager;
import com.dryseed.ds.util.log.DsLogger;

/**
 * Created by caiminming on 2017/10/27.
 */

final class DsWebInterface {
    private final WebDelegate DELEGATE;

    private DsWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static DsWebInterface create(WebDelegate delegate) {
        return new DsWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        DsLogger.d("WEB_EVENT",params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
