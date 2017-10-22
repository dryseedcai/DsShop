package com.dryseed.dsshop.example;

import android.app.Application;

import com.dryseed.ds.app.Ds;
import com.dryseed.dsshop.icon.FontDsModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by User on 2017/10/21.
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Ds.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontDsModule())
                .configure();
    }
}
