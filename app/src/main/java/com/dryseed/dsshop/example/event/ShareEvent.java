package com.dryseed.dsshop.example.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dryseed.ds.delegates.web.event.Event;
import com.dryseed.ds.util.log.DsLogger;

/**
 * Created by User on 2017/10/27.
 */

public class ShareEvent extends Event {

    @Override
    public String execute(String params) {

        DsLogger.json("ShareEvent", params);

        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");

//        ShareSDK.initSDK(getContext());
//        final OnekeyShare oks = new OnekeyShare();
//        oks.disableSSOWhenAuthorize();
//        oks.setTitle(title);
//        oks.setText(text);
//        oks.setImageUrl(imageUrl);
//        oks.setUrl(url);
//        oks.show(getContext());

        return null;
    }
}
