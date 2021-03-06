package com.dryseed.dsshop.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.dryseed.ds.activities.ProxyActivity;
import com.dryseed.ds.app.Ds;
import com.dryseed.ds.delegates.DsDelegate;
import com.dryseed.ds.ui.launcher.ILauncherListener;
import com.dryseed.ds.ui.launcher.OnLauncherFinishTag;
import com.dryseed.dsshop.launcher.LauncherDelegate;
import com.dryseed.dsshop.launcher.LauncherScrollDelegate;
import com.dryseed.dsshop.main.ShopBottomDelegate;
import com.dryseed.dsshop.main.index.IndexDelegate;
import com.dryseed.dsshop.sign.ISignListener;
import com.dryseed.dsshop.sign.SignUpDelegate;

import cn.jpush.android.api.JPushInterface;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by User on 2017/10/21.
 */
public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Ds.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true); //沉浸式状态栏
    }

    @Override
    public DsDelegate setRootDelegate() {
        //return new ExampleDelegate();
        return new LauncherDelegate();
        //return new SignUpDelegate();
    }

    @Override
    public void onSignInSuccess() {
        start(new ShopBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        start(new ShopBottomDelegate());
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag onLauncherFinishTag) {
        switch (onLauncherFinishTag) {
            case NOT_SIGNED:
                startWithPop(new SignUpDelegate());
                break;
            case SIGNED:
                startWithPop(new ShopBottomDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }
}
