package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhibeifw.frameworks.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/2 0002.
 */
public class BaseActivity extends ManagerBaseActivity {

    private List<OnBackPressedListener> mOnBackPressedListeners;

    @Override
    public void onBackPressed() {
        if (mOnBackPressedListeners != null) {
            for (OnBackPressedListener listener : mOnBackPressedListeners) {
                if (listener.onBackPressed())
                    return;
            }
        }
        super.onBackPressed();
    }

    /**
     * Adds a callback when back is pressed in this Activity.
     **/
    public void addOnBackPressedListener(OnBackPressedListener listener) {
        if (mOnBackPressedListeners == null) {
            mOnBackPressedListeners = new ArrayList<OnBackPressedListener>();
        }
        mOnBackPressedListeners.add(listener);
    }

    /**
     * Removes onBackPressed callback from this Activity.
     **/
    public void removeOnBackPressedListener(OnBackPressedListener listener) {
        if (mOnBackPressedListeners != null) {
            mOnBackPressedListeners.remove(listener);
        }
    }

    private Toolbar mToolbar;
    private static Field sTitleTextViewField;
    private static Field sNavButtonViewField;
    private static Field sMenuViewField;

    static {
        try {
            Class<?> toolbarClass = Toolbar.class;
            sTitleTextViewField = toolbarClass.getDeclaredField("mTitleTextView");
            sTitleTextViewField.setAccessible(true);
            sNavButtonViewField = toolbarClass.getDeclaredField("mNavButtonView");
            sNavButtonViewField.setAccessible(true);
            sMenuViewField = toolbarClass.getDeclaredField("mMenuView");
            sMenuViewField.setAccessible(true);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        try {
            TextView titleTextView = (TextView) sTitleTextViewField.get(getToolbar());
            ViewGroup.LayoutParams lp = titleTextView.getLayoutParams();
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            titleTextView.setGravity(Gravity.CENTER);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Toolbar getToolbar() {
        if (this.mToolbar != null) {
            return this.mToolbar;
        }
        return (Toolbar) this.getWindow().findViewById(R.id.action_bar);
    }

    public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
        try {
            View navButtonView = (View) sNavButtonViewField.get(getToolbar());
            if (showHomeAsUp) {
                navButtonView.setVisibility(View.VISIBLE);
            } else {
                navButtonView.setVisibility(View.INVISIBLE);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
