package com.dryseed.dsshop.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dryseed.ds.delegates.bottom.BottomItemDelegate;
import com.dryseed.dsshop.R;

/**
 * Created by caiminming on 2017/10/24.
 */

public class IndexDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
