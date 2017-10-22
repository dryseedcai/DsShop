package com.dryseed.ds.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 2017/10/21.
 */
public class Configurator {
    private static final HashMap<String, Object> DS_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    Configurator() {
        DS_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public static HashMap<String, Object> getDsConfigs() {
        return DS_CONFIGS;
    }

    public final void configure() {
        DS_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
        initIcons();
    }

    public final Configurator withApiHost(String host) {
        DS_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) DS_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) DS_CONFIGS.get(key);
    }
}
