package com.zhibeifw.zhibeiapplication.syllabus;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;

import com.android.volley.Request;
import com.zhibeifw.frameworks.app.ActionBarPullToRefreshListFragment;
import com.zhibeifw.frameworks.module.DaggerApplication;
import com.zhibeifw.zhibeiapplication.ZhibeiModule;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by Administrator on 2015/5/30 0030.
 */
public class OnlineRoomListFragment extends ActionBarPullToRefreshListFragment {
    private SyllabusComponent component;

    @Inject
    Provider<Request> requestProvider;

    @Inject
    Loader<Cursor> loader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.component = DaggerSyllabusComponent.builder()
                                                .applicationComponent(((DaggerApplication) getActivity().getApplication())
                                                                              .getComponent())
                                                .zhibeiModule(new ZhibeiModule(this))
                                                .build();

        this.component.inject(this);
    }

    @Override
    public void onRefreshStarted(View view) {
        requestProvider.get();
    }
}
