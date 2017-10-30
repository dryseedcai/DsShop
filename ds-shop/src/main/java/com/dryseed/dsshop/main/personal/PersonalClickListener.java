package com.dryseed.dsshop.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.dryseed.ds.delegates.DsDelegate;
import com.dryseed.dsshop.main.personal.list.ListBean;

/**
 * Created by caiminming on 2017/10/30.
 */

public class PersonalClickListener extends SimpleClickListener {

    private final DsDelegate DELEGATE;

    public PersonalClickListener(DsDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                DELEGATE.getParentDelegate().start(bean.getDelegate());
                break;
            case 2:
                DELEGATE.getParentDelegate().start(bean.getDelegate());
                break;
            default:
                break;
        }
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
