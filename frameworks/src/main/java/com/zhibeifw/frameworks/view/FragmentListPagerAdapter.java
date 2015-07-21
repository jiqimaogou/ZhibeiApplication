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

public class FragmentListPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private final List<T> fragments;

    public FragmentListPagerAdapter(FragmentManager fm, T... fragments) {
        super(fm);
        this.fragments = fragments == null ? new ArrayList<T>() : Arrays.asList(fragments);
    }

    public FragmentListPagerAdapter(FragmentManager fm, List<T> fragments) {
        super(fm);
        this.fragments = fragments == null ? new ArrayList<T>() : new ArrayList<T>(fragments);
    }

    @Override
    public T getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
