package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhibeifw.frameworks.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/6/19 0019.
 */
public class ViewPagerWithTabFragment extends ViewPagerFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_pager_with_tab_layout, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = ButterKnife.findById(view, R.id.tabbar);
        tabLayout.setupWithViewPager(getViewPager());
    }
}
