package com.dryseed.ds.delegates.web.event;


import com.dryseed.ds.util.log.DsLogger;

/**
 * Created by caiminming on 2017/10/27.
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        DsLogger.e("UndefineEvent", params);
        return null;
    }
}
