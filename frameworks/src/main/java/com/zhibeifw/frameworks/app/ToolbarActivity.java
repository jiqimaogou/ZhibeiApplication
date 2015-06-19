package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.zhibeifw.frameworks.R;

import butterknife.ButterKnife;

public class ToolbarActivity extends ActionBarActivity {

    private Toolbar mToolbar;

    private ViewGroup mToolbarContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbarLayout();
    }

    @Override
    public void setContentView(int layoutResID) {
        initToolbarLayout();
        getLayoutInflater().inflate(layoutResID, mToolbarContent);
    }

    private void initToolbarLayout() {
        super.setContentView(R.layout.toolbar_activity);
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
        mToolbarContent = ButterKnife.findById(this, getContentId());
        initToolBar();
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public int getContentId() {
        return R.id.toolbarContent;
    }

}
