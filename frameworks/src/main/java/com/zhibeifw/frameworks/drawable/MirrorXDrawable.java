package com.zhibeifw.frameworks.drawable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2015/4/9 0009.
 */
public class MirrorXDrawable extends DrawableWrapper {
    public MirrorXDrawable(Drawable drawable) {
        super(drawable);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Rect clip = canvas.getClipBounds();

        int cx = clip.centerX();
        int cy = clip.centerY();
    }


    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
    }
}
