package com.dryseed.dsshop.main;

import android.graphics.Color;

import com.dryseed.ds.delegates.bottom.BaseBottomDelegate;
import com.dryseed.ds.delegates.bottom.BottomItemDelegate;
import com.dryseed.ds.delegates.bottom.BottomTabBean;
import com.dryseed.ds.delegates.bottom.ItemBuilder;
import com.dryseed.dsshop.cart.ShopCartDelegate;
import com.dryseed.dsshop.main.discover.DiscoverDelegate;
import com.dryseed.dsshop.main.index.IndexDelegate;
import com.dryseed.dsshop.main.personal.PersonalDelegate;
import com.dryseed.dsshop.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by caiminming on 2017/10/24.
 */

public class ShopBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
