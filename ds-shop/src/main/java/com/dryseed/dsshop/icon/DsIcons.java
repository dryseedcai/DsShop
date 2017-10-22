package com.dryseed.dsshop.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by User on 2017/10/21.
 * <p/>
 * suan
 * &#xe743;
 * 
 * hua
 * &#xe744;
 * 
 * ju
 * &#xe745;
 */
public enum DsIcons implements Icon {
    suan('\ue743'),
    hua('\ue744'),
    ju('\ue745');

    private char character;

    DsIcons(char character) {
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