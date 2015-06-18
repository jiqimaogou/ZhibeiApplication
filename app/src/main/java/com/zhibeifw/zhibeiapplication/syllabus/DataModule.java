package com.zhibeifw.zhibeiapplication.syllabus;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ListAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.zhibeifw.frameworks.app.ActionBarPullToRefreshListFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2015/6/5 0005.
 */
@Module
public class DataModule {
    final Fragment fragment;

    protected DataModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    FragmentActivity provideActivity(Fragment fragment) {
        return fragment.getActivity();
    }

    @Provides
    Request provideRequest(Gson gson, RequestQueue requestQueue) {
        return null;
    }

    @Provides
    ModelQueriable provideModelQueriable() {
        return null;
    }

    @Provides
    ActionBarPullToRefreshListFragment provideActionBarPullToRefreshListFragment() {
        return (ActionBarPullToRefreshListFragment) fragment;
    }

    @Provides
    ListAdapter provideListAdapter(FragmentActivity activity, ModelQueriable modelQueriable) {
        return null;
    }

}
