package com.zhibeifw.frameworks.view;

import android.view.View;

/**
 * Created by Administrator on 2015/3/23 0023.
 */
public class ItemViewOnClickListener implements View.OnClickListener {

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        final ItemInfoHolder itemInfoHolder = (ItemInfoHolder) v.getTag();
        if (itemInfoHolder == null) {
            return;
        }
        Object[] objs = itemInfoHolder.objs;

        if (objs == null || objs.length == 0) {
            onClick(v, itemInfoHolder.position);   
        } else if (objs.length == 1) {
            onClick(v, itemInfoHolder.position, objs[0]);
        } else if (objs.length == 2) {
            onClick(v, itemInfoHolder.position, objs[0], objs[1]);
        } else {
            onClick(v, itemInfoHolder.position, objs);
        }
    }

    public void onClick(View v, int position) {
        
    }

    public void onClick(View v, int position, Object object) {
        
    }

    public void onClick(View v, int position, Object object1, Object object2) {
        
    }

    /**
     * Created by Administrator on 2015/3/23 0023.
     */
    public static class ItemInfoHolder {

        public int position;

        public Object[] objs;

        public ItemInfoHolder(int position, Object... objs) {
            this.position = position;
            this.objs = objs;
        }

        public ItemInfoHolder set(int position, Object... objs) {
            this.position = position;
            this.objs = objs;
            return this;
        }

        public static ItemInfoHolder attach(View v, int position, Object... objs) {
            ItemInfoHolder itemInfoHolder = (ItemInfoHolder) v.getTag();
            if (itemInfoHolder == null) {
                itemInfoHolder = new ItemInfoHolder(position, objs);
                v.setTag(itemInfoHolder);
            }
            itemInfoHolder.set(position, objs);
            return itemInfoHolder;
        }
    }
}
