package com.dryseed.dsshop.main.search;

import com.alibaba.fastjson.JSONArray;
import com.dryseed.ds.ui.recycler.DataConverter;
import com.dryseed.ds.ui.recycler.MultipleFields;
import com.dryseed.ds.ui.recycler.MultipleItemEntity;
import com.dryseed.ds.util.storage.DsPreference;

import java.util.ArrayList;

/**
 * Created by caiminming on 2017/11/1.
 */

public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr = DsPreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStr.equals("")) {
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }

        return ENTITIES;
    }
}
