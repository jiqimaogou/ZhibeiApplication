package com.zhibeifw.zhibeiapplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by Administrator on 2015/3/22 0022.
 */
@Module(injects = SyllabusListFragment.class)
public class ZhibeiModule {

    private static final String BASE_URL = "http://115.29.6.248:3200/api/v1";

    @Provides
    @Singleton
    RestAdapter provideRestAdapter() { 
        RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build(); 
        return restAdapter;
    }

    @Provides
    @Singleton
    ZhibeiService provideZhibeiService(RestAdapter restAdapter) { 
        return restAdapter.create(ZhibeiService.class);
    }
}
