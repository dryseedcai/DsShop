package com.dryseed.dsshop.example.generators;


import com.dryseed.ds.annotations.PayEntryGenerator;
import com.dryseed.ds.wechat.templates.WXPayEntryTemplate;

/**
 * Created by User on 2017/10/24
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.dryseed.dsshop.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
