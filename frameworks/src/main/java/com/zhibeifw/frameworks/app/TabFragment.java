package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/6/1 0001.
 */
public class TabFragment extends BaseFragment {

    private FragmentTabHost mFragmentTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentTabHost = onCreateFragmentTabHost(inflater, container, savedInstanceState);
        return mFragmentTabHost;
    }

    public FragmentTabHost onCreateFragmentTabHost(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return new FragmentTabHost(getActivity());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFragmentTabHost.setup(getActivity(), getChildFragmentManager(), getContainerId());
    }

    public int getContainerId() {
        return android.R.id.tabcontent;
    }
}
