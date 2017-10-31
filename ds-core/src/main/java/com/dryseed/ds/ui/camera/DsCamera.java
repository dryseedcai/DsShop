package com.dryseed.ds.ui.camera;

import android.net.Uri;

import com.dryseed.ds.delegates.PermissionCheckerDelegate;
import com.dryseed.ds.util.file.FileUtil;

/**
 * Created by caiminming on 2017/10/31.
 * 照相机调用类
 */

public class DsCamera {

    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image", FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
