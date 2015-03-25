package com.zhibeifw.zhibeiapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Administrator on 2015/3/22 0022.
 */
@Module(injects = SyllabusListFragment.class)
    public class ZhibeiModule {

        private static final String BASE_URL = "https://gentle-eyrie-3479.herokuapp.com";

        @Provides
        @Singleton
        RestAdapter provideRestAdapter() { 
            Gson gson = new GsonBuilder()
                // .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();
            RequestInterceptor requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestInterceptor.RequestFacade request) {
                    request.addHeader("Accept", "application/json");
                }
            };

            RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build(); 
            return restAdapter;
        }

        @Provides
        @Singleton
        ZhibeiService provideZhibeiService(RestAdapter restAdapter) { 
            return restAdapter.create(ZhibeiService.class);
        }
    }
