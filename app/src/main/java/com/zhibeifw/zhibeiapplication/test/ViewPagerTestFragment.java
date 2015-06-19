package com.zhibeifw.zhibeiapplication.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.zhibeifw.frameworks.app.MaterialViewPagerFragment;
import com.zhibeifw.frameworks.view.ArrayFragmentPagerAdapter;

import java.util.Arrays;

/**
 * Created by Administrator on 2015/6/19 0019.
 */
public class ViewPagerTestFragment extends MaterialViewPagerFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPagerAdapter(new ArrayFragmentPagerAdapter<String>(getChildFragmentManager(),
                                                              Arrays.asList("1", "2", "3", "4", "5")) {
            @Override
            public Fragment getItem(String item) {
                return new Fragment();
            }
        });
    }
}
