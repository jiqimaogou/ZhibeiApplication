package com.zhibeifw.frameworks.module;

import com.zhibeifw.frameworks.BaseApplication;
import com.zhibeifw.frameworks.dagger.ApplicationModule;

public class DaggerApplication extends BaseApplication {

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
