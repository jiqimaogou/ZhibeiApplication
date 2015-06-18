package com.zhibeifw.frameworks.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/3/23 0023.
 */
public class ImagePagerAdapter extends BasePagerAdapter implements View.OnClickListener {

    private int[] mImages;

    private Context mContext;

    private LayoutInflater mInflater;

    private ItemViewOnClickListener mClickListener = new ItemViewOnClickListener() {

        @Override
        public void onClick(View v, int position, Object object) {
            onClick(v, position, object);
        }
    };

    public ImagePagerAdapter(Context context, int... images) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImages = images;
    }

    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView view = getImageView(container, mInflater);
        view.setImageResource(mImages[position]);
        view.setOnClickListener(mClickListener);
        ItemViewOnClickListener.ItemInfoHolder itemInfoHolder = ItemViewOnClickListener.ItemInfoHolder.attach(view,
                                                                                                              position,
                                                                                                              mImages[position]);
        // added to ViewPager, container == ViewPager
        container.addView(view);
        return view;
    }

    protected ImageView getImageView(ViewGroup container, LayoutInflater inflater) {
        return new ImageView(mContext);
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
