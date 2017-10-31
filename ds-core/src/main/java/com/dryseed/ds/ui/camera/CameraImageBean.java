package com.dryseed.ds.ui.camera;

import android.net.Uri;

/**
 * Created by caiminming on 2017/10/31.
 * 存储一些中间值
 */

public final class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
