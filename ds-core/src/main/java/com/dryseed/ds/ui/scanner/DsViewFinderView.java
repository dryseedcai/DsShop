package com.dryseed.ds.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by caiminming on 2017/11/1.
 */

public class DsViewFinderView extends ViewFinderView {

    public DsViewFinderView(Context context) {
        this(context, null);
    }

    public DsViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder = true;
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);
    }
}
