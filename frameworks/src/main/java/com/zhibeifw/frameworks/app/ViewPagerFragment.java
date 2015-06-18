package com.zhibeifw.frameworks.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhibeifw.frameworks.R;

/**
 * Static library support version of the framework's {@link android.app.ViewPagerFragment}.
 * Used to write apps that run on platforms prior to Android 3.0.  When running
 * on Android 3.0 or above, this implementation is still used; it does not try
 * to switch to the framework's implementation.  See the framework SDK
 * documentation for a class overview.
 */
public class ViewPagerFragment extends Fragment {
    static final int INTERNAL_EMPTY_ID = 0x00ff0001;
    static final int INTERNAL_PROGRESS_CONTAINER_ID = 0x00ff0002;
    static final int INTERNAL_VIEW_PAGER_CONTAINER_ID = 0x00ff0003;

    final private Handler mHandler = new Handler();

    final private Runnable mRequestFocus = new Runnable() {
        public void run() {
            mViewPager.focusableViewAvailable(mViewPager);
        }
    };

    PagerAdapter mAdapter;
    ViewPager mViewPager;
    View mEmptyView;
    TextView mStandardEmptyView;
    View mProgressContainer;
    View mViewPagerContainer;
    CharSequence mEmptyText;
    boolean mViewPagerShown;

    public ViewPagerFragment() {
    }

    /**
     * Provide default implementation to return a simple ViewPager view.  Subclasses
     * can override to replace with their own layout.  If doing so, the
     * returned view hierarchy <em>must</em> have a ViewPager whose id
     * is {@link android.R.id#ViewPager R.id.viewPager} and can optionally
     * have a sibling view id {@link android.R.id#empty android.R.id.empty}
     * that is to be shown when the ViewPager is empty.
     * <p/>
     * <p>If you are overriding this method with your own custom content,
     * consider including the standard layout {@link R.layout#viewpager_content}
     * in your layout file, so that you continue to retain all of the standard
     * behavior of ViewPagerFragment.  In particular, this is currently the only
     * way to have the built-in indeterminant progress state be shown.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context context = getActivity();

        FrameLayout root = new FrameLayout(context);

        // ------------------------------------------------------------------

        LinearLayout pframe = new LinearLayout(context);
        pframe.setId(INTERNAL_PROGRESS_CONTAINER_ID);
        pframe.setOrientation(LinearLayout.VERTICAL);
        pframe.setVisibility(View.GONE);
        pframe.setGravity(Gravity.CENTER);

        ProgressBar progress = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        pframe.addView(progress,
                       new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));

        root.addView(pframe,
                     new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                                  ViewGroup.LayoutParams.FILL_PARENT));

        // ------------------------------------------------------------------

        FrameLayout lframe = new FrameLayout(context);
        lframe.setId(INTERNAL_VIEW_PAGER_CONTAINER_ID);

        TextView tv = new TextView(getActivity());
        tv.setId(INTERNAL_EMPTY_ID);
        tv.setGravity(Gravity.CENTER);
        lframe.addView(tv,
                       new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                                    ViewGroup.LayoutParams.FILL_PARENT));

        View lv = onCreateViewPager(inflater, container, savedInstanceState);
        lframe.addView(lv,
                       new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                                    ViewGroup.LayoutParams.FILL_PARENT));

        root.addView(lframe,
                     new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                                  ViewGroup.LayoutParams.FILL_PARENT));

        // ------------------------------------------------------------------

        root.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                                          ViewGroup.LayoutParams.FILL_PARENT));

        return root;
    }

    public View onCreateViewPager(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewPager viewPager = new ViewPager(getActivity());
        viewPager.setId(R.id.viewPager);

        return viewPager;
    }

    /**
     * Attach to ViewPager view once the view hierarchy has been created.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ensureViewPager();
    }

    /**
     * Detach from ViewPager view.
     */
    @Override
    public void onDestroyView() {
        mHandler.removeCallbacks(mRequestFocus);
        mViewPager = null;
        mViewPagerShown = false;
        mEmptyView = mProgressContainer = mViewPagerContainer = null;
        mStandardEmptyView = null;
        super.onDestroyView();
    }

    /**
     * This method will be called when an item in the ViewPager is selected.
     * Subclasses should override. Subclasses can call
     * getViewPagerView().getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param l        The ViewPager where the click happened
     * @param v        The view that was clicked within the ViewPager
     * @param position The position of the view in the ViewPager
     * @param id       The row id of the item that was clicked
     */
    public void onViewPagerItemClick(ViewPager l, View v, int position, long id) {
    }

    /**
     * Provide the cursor for the ViewPager view.
     */
    public void setPagerAdapter(PagerAdapter adapter) {
        boolean hadAdapter = mAdapter != null;
        mAdapter = adapter;
        if (mViewPager != null) {
            mViewPager.setAdapter(adapter);
            if (!mViewPagerShown && !hadAdapter) {
                // The ViewPager was hidden, and previously didn't have an
                // adapter.  It is now time to show it.
                setViewPagerShown(true, getView().getWindowToken() != null);
            }
        }
    }

    /**
     * Get the activity's ViewPager view widget.
     */
    public ViewPager getViewPager() {
        ensureViewPager();
        return mViewPager;
    }

    /**
     * The default content for a ViewPagerFragment has a TextView that can
     * be shown when the ViewPager is empty.  If you would like to have it
     * shown, call this method to supply the text it should use.
     */
    public void setEmptyText(CharSequence text) {
        ensureViewPager();
        if (mStandardEmptyView == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        mStandardEmptyView.setText(text);
        mEmptyText = text;
    }

    /**
     * Control whether the ViewPager is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     * <p/>
     * <p>Applications do not normally need to use this themselves.  The default
     * behavior of ViewPagerFragment is to start with the ViewPager not being shown, only
     * showing it once an adapter is given with {@link #setPagerAdapter(PagerAdapter)}.
     * If the ViewPager at that point had not been shown, when it does get shown
     * it will be do without the user ever seeing the hidden state.
     *
     * @param shown If true, the ViewPager view is shown; if false, the progress
     *              indicator.  The initial value is true.
     */
    public void setViewPagerShown(boolean shown) {
        setViewPagerShown(shown, true);
    }

    /**
     * Like {@link #setViewPagerShown(boolean)}, but no animation is used when
     * transitioning from the previous state.
     */
    public void setViewPagerShownNoAnimation(boolean shown) {
        setViewPagerShown(shown, false);
    }

    /**
     * Control whether the ViewPager is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     *
     * @param shown   If true, the ViewPager view is shown; if false, the progress
     *                indicator.  The initial value is true.
     * @param animate If true, an animation will be used to transition to the
     *                new state.
     */
    private void setViewPagerShown(boolean shown, boolean animate) {
        ensureViewPager();
        if (mProgressContainer == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        if (mViewPagerShown == shown) {
            return;
        }
        mViewPagerShown = shown;
        if (shown) {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
                mViewPagerContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
            } else {
                mProgressContainer.clearAnimation();
                mViewPagerContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(View.GONE);
            mViewPagerContainer.setVisibility(View.VISIBLE);
        } else {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
                mViewPagerContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(),
                                                                                android.R.anim.fade_out));
            } else {
                mProgressContainer.clearAnimation();
                mViewPagerContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(View.VISIBLE);
            mViewPagerContainer.setVisibility(View.GONE);
        }
    }

    /**
     * Get the PagerAdapter associated with this activity's ViewPager.
     */
    public PagerAdapter getPagerAdapter() {
        return mAdapter;
    }

    private void ensureViewPager() {
        if (mViewPager != null) {
            return;
        }
        View root = getView();
        if (root == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if (root instanceof ViewPager) {
            mViewPager = (ViewPager) root;
        } else {
            mStandardEmptyView = (TextView) root.findViewById(INTERNAL_EMPTY_ID);
            if (mStandardEmptyView == null) {
                mEmptyView = root.findViewById(android.R.id.empty);
            } else {
                mStandardEmptyView.setVisibility(View.GONE);
            }
            mProgressContainer = root.findViewById(INTERNAL_PROGRESS_CONTAINER_ID);
            mViewPagerContainer = root.findViewById(INTERNAL_VIEW_PAGER_CONTAINER_ID);
            View rawViewPager = root.findViewById(R.id.viewPager);
            if (!(rawViewPager instanceof ViewPager)) {
                if (rawViewPager == null) {
                    throw new RuntimeException("Your content must have a ViewPager whose id attribute is " + "'R.id.viewPager'");
                }
                throw new RuntimeException("Content has view with id attribute 'R.id.viewPager' " + "that is not a ViewPager class");
            }
            mViewPager = (ViewPager) rawViewPager;
            if (mEmptyText != null) {
                mStandardEmptyView.setText(mEmptyText);
            }
        }
        mViewPagerShown = true;
        if (mAdapter != null) {
            PagerAdapter adapter = mAdapter;
            mAdapter = null;
            setPagerAdapter(adapter);
        } else {
            // We are starting without an adapter, so assume we won't
            // have our data right away and start with the progress indicator.
            if (mProgressContainer != null) {
                setViewPagerShown(false, false);
            }
        }
        mHandler.post(mRequestFocus);
    }
}
