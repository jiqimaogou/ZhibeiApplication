package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.zhibeifw.frameworks.R;

/**
 * Created by Administrator on 2015/6/19 0019.
 */
public class MaterialViewPagerFragment extends ViewPagerFragment {

    private MaterialViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewPager = (MaterialViewPager) inflater.inflate(R.layout.view_pager_with_material, container, false);
        return mViewPager;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
    }
}
