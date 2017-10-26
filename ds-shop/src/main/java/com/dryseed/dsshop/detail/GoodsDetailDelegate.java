package com.dryseed.dsshop.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.dryseed.ds.delegates.DsDelegate;
import com.dryseed.dsshop.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by caiminming on 2017/10/26.
 */

public class GoodsDetailDelegate extends DsDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
