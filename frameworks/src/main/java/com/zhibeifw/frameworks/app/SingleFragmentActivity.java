package com.zhibeifw.frameworks.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.zhibeifw.frameworks.R;

public class SingleFragmentActivity extends BaseActivity implements FragmentStack.Callback {

    private FragmentStack mStack;

    public String getFname() {
        return getIntent().getStringExtra(Fragment.class.getSimpleName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mStack = FragmentStack.forContainer(this, getFragmentContainer(), this);

        if (savedInstanceState == null) {
            try {
                String fname = getFname();
                mStack.replace(Class.forName(fname), fname, getIntent().getExtras());
                mStack.commit();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
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
    public void onStackChanged(int stackSize, Fragment topFragment) {

    }

    public FragmentStack getStack() {
        return mStack;
    }

    public int getFragmentContainer() {
        return R.id.fragmentContainer;
    }

    @Override
    public void onBackPressed() {
        if (!getStack().pop(true)) {
            super.onBackPressed();
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        Fragment fragment = mStack.peek();
        fragment.startActivityForResult(intent, requestCode);
    }
}
