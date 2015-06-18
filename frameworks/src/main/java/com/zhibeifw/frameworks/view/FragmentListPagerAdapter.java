package com.zhibeifw.frameworks.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/6/4 0004.
 */

public class FragmentListPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments;

    public FragmentListPagerAdapter(FragmentManager fm, Fragment... fragments) {
        super(fm);
        this.fragments = fragments == null ? new ArrayList<Fragment>() : Arrays.asList(fragments);
    }

    public FragmentListPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments == null ? new ArrayList<Fragment>() : new ArrayList<Fragment>(fragments);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
