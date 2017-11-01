package com.dryseed.dsshop.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dryseed.ds.delegates.bottom.BottomItemDelegate;
import com.dryseed.ds.ui.recycler.decoration.BaseDecoration;
import com.dryseed.ds.ui.refresh.RefreshHandler;
import com.dryseed.ds.util.callback.CallbackManager;
import com.dryseed.ds.util.callback.CallbackType;
import com.dryseed.ds.util.callback.IGlobalCallback;
import com.dryseed.dsshop.R;
import com.dryseed.dsshop.R2;
import com.dryseed.ds.debug.RequestData;
import com.dryseed.dsshop.main.ShopBottomDelegate;
import com.dryseed.dsshop.main.search.SearchDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by caiminming on 2017/10/24.
 */

public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    EditText mSearchView = null;

    private RefreshHandler mRefreshHandler;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallback(@Nullable String args) {
                        Toast.makeText(getContext(), "得到的二维码是" + args, Toast.LENGTH_LONG).show();
                    }
                });
        mSearchView.setOnFocusChangeListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.d("MMM", "IndexDelegate onLazyInitView");
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage(RequestData.INDEX_DATA.name());
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        // true : 下拉进度圆球会由小变大 ; 120:起始出现高度 300：结束高度
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        final ShopBottomDelegate shopBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(shopBottomDelegate));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MMM", "IndexDelegate onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("MMM", "IndexDelegate onPause");
    }

    @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
        startScanWithCheck(this.getParentDelegate());
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            getParentDelegate().start(new SearchDelegate());
        }
    }
}
