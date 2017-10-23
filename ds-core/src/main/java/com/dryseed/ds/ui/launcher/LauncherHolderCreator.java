package com.dryseed.ds.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by caiminming on 2017/10/23.
 */

public class LauncherHolderCreator implements CBViewHolderCreator {
    @Override
    public Object createHolder() {
        return new LauncherHolder();
    }
}
