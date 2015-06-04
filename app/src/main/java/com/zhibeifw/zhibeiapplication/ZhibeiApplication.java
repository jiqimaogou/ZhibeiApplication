package com.zhibeifw.zhibeiapplication;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.zhibeifw.frameworks.module.DaggerApplication;

public class ZhibeiApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        FlowManager.destroy();
    }

}
