package com.dryseed.dsshop.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.dryseed.ds.delegates.DsDelegate;
import com.dryseed.ds.ui.recycler.MultipleFields;
import com.dryseed.ds.ui.recycler.MultipleItemEntity;
import com.dryseed.dsshop.detail.GoodsDetailDelegate;
import com.dryseed.dsshop.launcher.LauncherDelegate;

/**
 * Created by caiminming on 2017/10/24.
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final DsDelegate DELEGATE;

    private IndexItemClickListener(DsDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(DsDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        DELEGATE.start(delegate);
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
