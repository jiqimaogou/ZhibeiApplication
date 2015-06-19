package com.zhibeifw.frameworks.app.drawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.model.BaseDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.zhibeifw.frameworks.app.FragmentStack;

/**
 * Created by Administrator on 2015/6/18 0018.
 */
public abstract class FragmentDrawerActivity extends DrawerActivity implements FragmentStack.Callback {

    private FragmentStack mStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStack = FragmentStack.forContainer(this, getContentId(), this);

        if (savedInstanceState == null) {
        } else {
            mStack.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mStack.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
        mStack.push((Class) iDrawerItem.getTag(), ((BaseDrawerItem) iDrawerItem).getName());
        mStack.commit();
        return false;
    }

    @Override
    public void onStackChanged(int stackSize, Fragment topFragment) {
    }

    public FragmentStack getFragmentStack() {
        return mStack;
    }
}
