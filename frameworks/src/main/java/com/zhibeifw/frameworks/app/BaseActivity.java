package com.zhibeifw.frameworks.app;

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
}
