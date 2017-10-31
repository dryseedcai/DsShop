package com.dryseed.ds.util.callback;

import android.support.annotation.Nullable;

/**
 * Created by caiminming on 2017/10/31.
 */

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
