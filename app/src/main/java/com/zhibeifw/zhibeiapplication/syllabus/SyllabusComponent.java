package com.zhibeifw.zhibeiapplication.syllabus;

import com.android.volley.Request;
import com.zhibeifw.frameworks.module.ActivityScope;
import com.zhibeifw.frameworks.module.ApplicationComponent;
import com.zhibeifw.zhibeiapplication.ZhibeiModule;

import dagger.Component;

/**
 * Created by Administrator on 2015/5/30 0030.
 */
@ActivityScope
@Component(modules = {ZhibeiModule.class}, dependencies = ApplicationComponent.class)
public interface SyllabusComponent {
    OnlineRoomListFragment inject(OnlineRoomListFragment onlineRoomListFragment);

    Request getRequest();
}
