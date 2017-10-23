package com.dryseed.dsshop.example;

import android.widget.Toast;

import com.dryseed.ds.activities.ProxyActivity;
import com.dryseed.ds.delegates.DsDelegate;
import com.dryseed.ds.ui.launcher.ILauncherListener;
import com.dryseed.ds.ui.launcher.OnLauncherFinishTag;
import com.dryseed.dsshop.launcher.LauncherDelegate;
import com.dryseed.dsshop.launcher.LauncherScrollDelegate;
import com.dryseed.dsshop.sign.ISignListener;
import com.dryseed.dsshop.sign.SignUpDelegate;

/**
 * Created by User on 2017/10/21.
 */
public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener{

    @Override
    public DsDelegate setRootDelegate() {
        //return new ExampleDelegate();
        return new LauncherDelegate();
        //return new SignUpDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag onLauncherFinishTag) {
        switch (onLauncherFinishTag){
            case NOT_SIGNED:
                startWithPop(new SignUpDelegate());
                break;
            case SIGNED:
                startWithPop(new ExampleDelegate());
                break;
            default:
                break;
        }
    }
}
