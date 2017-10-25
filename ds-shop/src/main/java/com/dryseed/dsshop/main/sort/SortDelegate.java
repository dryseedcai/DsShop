package com.dryseed.dsshop.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.dryseed.ds.delegates.bottom.BottomItemDelegate;
import com.dryseed.dsshop.R;

/**
 * Created by caiminming on 2017/10/24.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.d("MMM", "SortDelegate onLazyInitView");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MMM", "SortDelegate onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MMM", "SortDelegate onPause");
    }
}
