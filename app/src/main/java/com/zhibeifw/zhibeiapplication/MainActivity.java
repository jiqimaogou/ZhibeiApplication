package com.zhibeifw.zhibeiapplication;

import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.zhibeifw.frameworks.app.DrawerActivity;

public class MainActivity extends DrawerActivity {

    @Override
    protected void addDrawerItems(DrawerBuilder drawerBuilder) {

    }

    @Override
    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
        return false;
    }

}
