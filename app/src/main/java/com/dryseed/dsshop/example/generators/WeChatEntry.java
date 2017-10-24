package com.dryseed.dsshop.example.generators;


import com.dryseed.ds.annotations.EntryGenerator;
import com.dryseed.ds.wechat.templates.WXEntryTemplate;

/**
 * Created by User on 2017/10/24
 */

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.dryseed.dsshop.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
