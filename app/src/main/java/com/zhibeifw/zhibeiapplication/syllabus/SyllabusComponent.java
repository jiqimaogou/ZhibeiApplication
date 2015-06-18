package com.zhibeifw.zhibeiapplication.syllabus;

import com.android.volley.Request;
import com.zhibeifw.frameworks.module.ActivityScope;
import com.zhibeifw.frameworks.module.ApplicationComponent;

import dagger.Component;

/**
 * Created by Administrator on 2015/5/30 0030.
 */
@ActivityScope
@Component(modules = {DataModule.class}, dependencies = ApplicationComponent.class)
public interface SyllabusComponent {
    void inject(OnlineRoomListFragment fragment);

    Request getRequest();
}
