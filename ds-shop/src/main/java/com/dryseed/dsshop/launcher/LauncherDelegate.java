package com.dryseed.dsshop.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dryseed.ds.app.AccountManager;
import com.dryseed.ds.app.IUserChecker;
import com.dryseed.ds.delegates.DsDelegate;
import com.dryseed.ds.ui.launcher.ILauncherListener;
import com.dryseed.ds.ui.launcher.OnLauncherFinishTag;
import com.dryseed.ds.ui.launcher.ScrollLauncherTag;
import com.dryseed.ds.util.storage.DsPreference;
import com.dryseed.ds.util.timer.BaseTimerTask;
import com.dryseed.ds.util.timer.ITimerListener;
import com.dryseed.dsshop.R;
import com.dryseed.dsshop.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by User on 2017/10/22.
 */
public class LauncherDelegate extends DsDelegate implements ITimerListener {

    private ILauncherListener mILauncherListener;

    @BindView(R2.id.tv_launcher_timer)
    TextView mTvTimer;

    private Timer mTimer;
    private int mCount = 5;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        checkIsShowScroll();
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (!DsPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否登录了APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
