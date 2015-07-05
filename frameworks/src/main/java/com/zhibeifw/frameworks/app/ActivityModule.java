package com.zhibeifw.frameworks.app;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module
final class ActivityModule {

    private final Activity activity;

    ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity activity() {
        return activity;
    }
}
