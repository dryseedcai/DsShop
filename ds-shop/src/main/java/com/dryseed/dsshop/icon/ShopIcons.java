package com.dryseed.dsshop.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by caiminming on 2017/10/25.
 */

public enum ShopIcons implements Icon {
    suan('\ue743'),
    hua('\ue744'),
    ju('\ue745'),
    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    private char character;

    ShopIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
