package com.dryseed.dsshop.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by User on 2017/10/21.
 */
public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Ds.getConfigurator().withActivity(this);
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
        switch (onLauncherFinishTag){
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
}
