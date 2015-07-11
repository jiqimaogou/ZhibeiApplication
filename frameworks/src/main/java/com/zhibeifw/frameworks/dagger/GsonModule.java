package com.zhibeifw.frameworks.dagger;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2015/7/2 0002.
 */
@Module
public class GsonModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return Converters.registerDateTime(new GsonBuilder().excludeFieldsWithoutExposeAnnotation()).create();
    }
}
