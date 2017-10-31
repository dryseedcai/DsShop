package com.dryseed.dsshop.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.dryseed.ds.delegates.DsDelegate;
import com.dryseed.dsshop.R;

/**
 * Created by caiminming on 2017/10/31.
 */

public class NameDelegate extends DsDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
