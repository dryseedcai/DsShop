package com.dryseed.dsshop.example;

import com.dryseed.ds.activities.ProxyActivity;
import com.dryseed.ds.delegates.DsDelegate;
import com.dryseed.dsshop.launcher.LauncherDelegate;

/**
 * Created by User on 2017/10/21.
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    public DsDelegate setRootDelegate() {
        //return new ExampleDelegate();
        return new LauncherDelegate();
    }
}
