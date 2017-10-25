package com.dryseed.ds.ui.recycler;

import java.util.LinkedHashMap;

/**
 * Created by caiminming on 2017/10/24.
 */

public class MultipleEntityBuilder {

    private final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder() {
        //先清除之前的数据
        FIELDS.clear();
    }

    public final MultipleEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleEntityBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }
}
