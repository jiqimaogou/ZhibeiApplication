package com.zhibeifw.frameworks.app.drawer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.zhibeifw.frameworks.app.ToolbarActivity;

/**
 * Created by Administrator on 2015/6/18 0018.
 */
public abstract class DrawerActivity extends ToolbarActivity implements Drawer.OnDrawerItemClickListener {

    private Drawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawerBuilder drawerBuilder = new DrawerBuilder().withActivity(this).withToolbar(getToolbar());
        addDrawerItems(drawerBuilder);
        mDrawer = drawerBuilder.withOnDrawerItemClickListener(this).build();
    }

    protected abstract void addDrawerItems(DrawerBuilder drawerBuilder);

    @Override
    public abstract boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem);

    public Drawer getDrawer() {
        return mDrawer;
    }
}
