package com.dryseed.dsshop.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.dryseed.ds.debug.RequestData;
import com.dryseed.ds.delegates.DsDelegate;
import com.dryseed.ds.net.RestClient;
import com.dryseed.ds.net.callback.ISuccess;
import com.dryseed.ds.ui.date.DateDialogUtil;
import com.dryseed.ds.util.callback.CallbackManager;
import com.dryseed.ds.util.callback.CallbackType;
import com.dryseed.ds.util.callback.IGlobalCallback;
import com.dryseed.ds.util.log.DsLogger;
import com.dryseed.dsshop.R;
import com.dryseed.dsshop.main.personal.list.ListBean;

/**
 * Created by caiminming on 2017/10/31.
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final UserProfileDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "保密"};
    private int selectedItem = 0;

    public UserProfileClickListener(UserProfileDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(final BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                //设置头像，开始照相机或选择图片
                CallbackManager.getInstance()
                        .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                            @Override
                            public void executeCallback(Uri args) {
                                DsLogger.d("ON_CROP", args); //file:///storage/emulated/0/DCIM/Camera/IMG_20171031_163714.jpg
                                final ImageView avatar = (ImageView) view.findViewById(R.id.img_arrow_avatar);
                                Glide.with(DELEGATE)
                                        .load(args)
                                        .into(avatar);

                                RestClient.builder()
                                        //.url(UploadConfig.UPLOAD_IMG)
                                        .url(RequestData.UPLOAD_IMG.name())
                                        .loader(DELEGATE.getContext())
                                        .file(args.getPath())
                                        .success(new ISuccess() {
                                            @Override
                                            public void onSuccess(String response) {
                                                DsLogger.d("ON_CROP_UPLOAD", response);
                                                //final String path = JSON.parseObject(response).getJSONObject("result").getString("path");

                                                //通知服务器更新信息
                                                RestClient.builder()
                                                        .url(RequestData.USER_PROFILE.name())
                                                        //.params("avatar", path)
                                                        .loader(DELEGATE.getContext())
                                                        .success(new ISuccess() {
                                                            @Override
                                                            public void onSuccess(String response) {
                                                                //获取更新后的用户信息，然后更新本地数据库
                                                                //没有本地数据的APP，每次打开APP都请求API，获取信息
                                                            }
                                                        })
                                                        .build()
                                                        .post();
                                            }
                                        })
                                        .build()
                                        .upload();
                            }
                        });
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final DsDelegate nameDelegate = bean.getDelegate();
                DELEGATE.start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new View.OnClickListener() {
                    @Override
                    public void onClick(View nullView) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGenders[selectedItem]);
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(final View.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, selectedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selectedItem = i;
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (null != listener) {
                    listener.onClick(null);
                }
            }
        });
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
