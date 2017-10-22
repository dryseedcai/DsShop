package com.dryseed.ds.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Dryseed on 2017/10/21.
 */
public final class Ds {
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getDsConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
}
