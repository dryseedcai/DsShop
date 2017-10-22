package com.dryseed.ds.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Dryseed on 2017/10/21.
 */
public final class Ds {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getDsConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
