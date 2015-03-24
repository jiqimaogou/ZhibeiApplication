package com.zhibeifw.zhibeiapplication;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.zhibeifw.frameworks.app.DaggerApplication;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected List<Object> getModules() {
        List<Object> modules = new ArrayList<Object>();
        modules.add(new ZhibeiModule());
        return modules;
    }
}
