package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhibeifw.frameworks.R;

/**
 * Created by Administrator on 2015/6/19 0019.
 */
public abstract class BottomTabsFragment extends TabsFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_tabs, container, false);
    }

    @Override
    public int getContainerId() {
        return R.id.realtabcontent;
    }
}
