package com.zhibeifw.frameworks;

import com.easemob.chatuidemo.HXApplication;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Administrator on 2015/6/19 0019.
 */
public class BaseApplication extends HXApplication {

    private static BaseApplication sInstance;

    public synchronized static BaseApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        FlowManager.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        FlowManager.destroy();
    }
}
