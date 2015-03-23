package com.zhibeifw.frameworks.app;

import android.app.Application;

import com.zhibeifw.frameworks.module.AndroidModule;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Administrator on 2015/3/22 0022.
 */
public class DaggerApplication extends Application {
    private ObjectGraph graph;

    @Override public void onCreate() {
        super.onCreate();

        // graph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        List<Object> modules = new ArrayList<Object>();
        modules.add(new AndroidModule(this));
        modules.addAll(getApplicationModules());
        return modules;
    }

    public void inject(Object object) {
        graph.inject(object);
    }

    protected List<Object> getApplicationModules() {
        List<Object> applicationModules = new ArrayList<Object>();
        return applicationModules;
    }
}
