package com.dryseed.dsshop.example.generators;


import com.dryseed.ds.annotations.AppRegisterGenerator;
import com.dryseed.ds.wechat.templates.AppRegisterTemplate;

/**
 * Created by User on 2017/10/24
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.dryseed.dsshop.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
