package com.zhibeifw.frameworks.module;

import android.app.Application;

public class DaggerApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        this.component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        this.component.injectApplication(this);
    }


    public ApplicationComponent getComponent() {
        return this.component;
    }
}  
