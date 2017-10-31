package com.dryseed.dsshop.main.personal.address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dryseed.ds.debug.RequestData;
import com.dryseed.ds.delegates.DsDelegate;
import com.dryseed.ds.net.RestClient;
import com.dryseed.ds.net.callback.ISuccess;
import com.dryseed.ds.ui.recycler.MultipleItemEntity;
import com.dryseed.ds.util.log.DsLogger;
import com.dryseed.dsshop.R;
import com.dryseed.dsshop.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by caiminming on 2017/10/30.
 */

public class AddressDelegate extends DsDelegate implements ISuccess{
    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RestClient.builder()
                .url(RequestData.ADDRESS.name())
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        DsLogger.d("AddressDelegate", response);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data = new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}
