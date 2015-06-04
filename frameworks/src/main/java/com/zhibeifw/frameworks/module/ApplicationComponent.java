package com.zhibeifw.frameworks.module;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.zhibeifw.frameworks.volley.VolleyModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, VolleyModule.class})
public interface ApplicationComponent {
    DaggerApplication injectApplication(DaggerApplication application);

    RequestQueue getRequestQueue();

    Gson getGson();
}
