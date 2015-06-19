package com.zhibeifw.frameworks.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/6/18 0018.
 */
public abstract class TabsFragment extends Fragment {

    private FragmentTabHost mTabHost;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        return mTabHost;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTabHost.setup(getActivity(), getChildFragmentManager(), getContainerId());
        addTab(getTabHost());
    }

    protected abstract void addTab(FragmentTabHost tabHost);

    public int getContainerId() {
        return android.R.id.tabcontent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getCurrentFragment().onActivityResult(requestCode, resultCode, data);
    }

    public Fragment getCurrentFragment() {
        return getChildFragmentManager().findFragmentById(getContainerId());
    }

    public FragmentTabHost getTabHost() {
        if (mTabHost == null) {
            mTabHost = ButterKnife.findById(getView(), android.R.id.tabhost);
        }

        return mTabHost;
    }
}
