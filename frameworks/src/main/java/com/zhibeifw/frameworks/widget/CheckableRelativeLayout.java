package com.zhibeifw.frameworks.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;

public class CheckableRelativeLayout extends RelativeLayout implements Checkable {

    private Checkable checkbox;

    public CheckableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int childCount = getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View v = getChildAt(i);

            if (v instanceof Checkable) {
                checkbox = (Checkable) v;
                break;
            }
        }
    }

    @Override
    public boolean isChecked() {
        return checkbox != null ? checkbox.isChecked() : false;
    }

    @Override
    public void setChecked(boolean checked) {
        if (checkbox != null) {
            checkbox.setChecked(checked);
        }
    }

    @Override
    public void toggle() {
        if (checkbox != null) {
            checkbox.toggle();
        }
    }
}
