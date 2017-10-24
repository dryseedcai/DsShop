package com.dryseed.ds.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.dryseed.ds.R;
import com.dryseed.ds.delegates.DsDelegate;

/**
 * Created by caiminming on 2017/10/24.
 */

public abstract class BottomItemDelegate extends DsDelegate implements View.OnKeyListener {

    private long mTouchTime = 0;
    private final int WAIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mTouchTime) < WAIT_TIME) {
                _mActivity.finish();
            } else {
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT);
                mTouchTime = System.currentTimeMillis();
            }
            return true;
        }
        return false;
    }
}
