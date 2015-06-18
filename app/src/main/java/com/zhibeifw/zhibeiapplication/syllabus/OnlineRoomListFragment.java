package com.zhibeifw.zhibeiapplication.syllabus;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;

import com.android.volley.Request;
import com.zhibeifw.frameworks.app.ActionBarPullToRefreshListFragment;
import com.zhibeifw.frameworks.module.DaggerApplication;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by Administrator on 2015/5/30 0030.
 */
public class OnlineRoomListFragment extends ActionBarPullToRefreshListFragment {
    private SyllabusComponent component;

    private OnlineRoom onlineRoom;

    @Inject
    Provider<Request> requestProvider;

    @Inject
    ListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            onlineRoom = getArguments().getParcelable(OnlineRoom.class.getName());
        }
        this.component = DaggerSyllabusComponent.builder()
                                                .applicationComponent(((DaggerApplication) getActivity().getApplication())
                                                                              .getComponent())
                                                .dataModule(new SyllabusModule(this))
                                                .build();

        this.component.inject(this);
        setListAdapter(adapter);
    }

    @Override
    public void onRefreshStarted(View view) {
        requestProvider.get();
    }
}
