package com.zhibeifw.frameworks.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhibeifw.frameworks.R;

/**
 * Created by Administrator on 2015/3/23 0023.
 */
public class ImageAdapter extends PagerAdapter implements View.OnClickListener {

    private int[] images;

    private Context mContext;

    private LayoutInflater mInflater;

    private ItemViewOnClickListener mClickListener = new ItemViewOnClickListener() {

        @Override
        public void onClick(View v, int position, Object object) {
            onClick(v, position, object);
        }
    };

    public ImageAdapter(Context context, int... images) {
        this.mContext = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == (View) arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView view =
            (ImageView) mInflater.inflate(
                    R.layout.list_item_image, null);
        view.setImageResource(images[position]);
        view.setOnClickListener(mClickListener);
        ItemViewOnClickListener.ItemInfoHolder itemInfoHolder = ItemViewOnClickListener.ItemInfoHolder.attach(view, position, images[position]);
        // added to ViewPager, container == ViewPager
        container.addView(view);
        return view;
    }

    @Override
    public void
    destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        
    }

    public void onClick(View v, int position, Object object) {
        
    }
}
