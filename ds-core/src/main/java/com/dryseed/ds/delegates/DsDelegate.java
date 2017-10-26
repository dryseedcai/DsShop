package com.dryseed.ds.delegates;

/**
 * Created by User on 2017/10/21.
 */
public abstract class DsDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends DsDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
