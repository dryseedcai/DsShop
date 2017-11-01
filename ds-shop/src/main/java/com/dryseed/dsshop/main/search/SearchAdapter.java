package com.dryseed.dsshop.main.search;

import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;


import com.dryseed.ds.ui.recycler.MultipleFields;
import com.dryseed.ds.ui.recycler.MultipleItemEntity;
import com.dryseed.ds.ui.recycler.MultipleRecyclerAdapter;
import com.dryseed.ds.ui.recycler.MultipleViewHolder;
import com.dryseed.dsshop.R;

import java.util.List;

/**
 * Created by caiminming on 2017/11/1.
 */

public class SearchAdapter extends MultipleRecyclerAdapter {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()) {
            case SearchItemType.ITEM_SEARCH:
                final TextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
