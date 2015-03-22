package com.zhibeifw.frameworks.app.test;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.zhibeifw.frameworks.app.ActionBarPullToRefreshListFragment;

/**
 * Created by Administrator on 2015/3/22 0022.
 */
public class ActionBarPullToRefreshListFragmentTest extends ActionBarPullToRefreshListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, new String[]{"abc","efg","hij","klm"}));
    }
}
