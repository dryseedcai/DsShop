package com.dryseed.dsshop.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by User on 2017/10/21.
 *
 * 参考：http://www.cnblogs.com/zyw-205520/p/7266225.html?utm_source=debugrun&utm_medium=referral
 */
public class FontDsModule implements IconFontDescriptor{
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return DsIcons.values();
    }
}
