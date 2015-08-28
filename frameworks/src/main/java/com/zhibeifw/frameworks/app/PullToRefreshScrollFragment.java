package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.zhibeifw.frameworks.R;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/7/10 0010.
 */
public class PullToRefreshScrollFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2 {

    private PullToRefreshBase<ScrollView> mPullToRefreshListView;

    final private Handler mHandler = new Handler();

    final private Runnable mRequestRefresh = new Runnable() {
        public void run() {
            if (mPullToRefreshListView != null) {
                mPullToRefreshListView.setRefreshing();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        autoRefresh();
    }

    protected void autoRefresh() {
        mHandler.postDelayed(mRequestRefresh, 500);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup parent = (ViewGroup) view.getParent();

        // Remove ListView and add PullToRefreshListView in its place
        int lvIndex = parent.indexOfChild(view);
        parent.removeViewAt(lvIndex);
        mPullToRefreshListView = onCreatePullToRefreshListView(getLayoutInflater(savedInstanceState),
                                                               savedInstanceState);
        // Set a listener to be invoked when the list should be refreshed.
        mPullToRefreshListView.setOnRefreshListener(this);
        parent.addView(mPullToRefreshListView, lvIndex, view.getLayoutParams());

        mPullToRefreshListView.addView(view);
        setView(mPullToRefreshListView);
    }

    public void setView(View v) {
        Field field;
        try {
            field = Fragment.class.getDeclaredField("mView");
            field.setAccessible(true);
            field.set(this, v);
            field = Fragment.class.getDeclaredField("mInnerView");
            field.setAccessible(true);
            field.set(this, v);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected PullToRefreshBase onCreatePullToRefreshListView(LayoutInflater inflater, Bundle savedInstanceState) {
        return (PullToRefreshBase) inflater.inflate(R.layout.ptr_scrollview, null);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {
    }

    /**
     * @return The {@link PullToRefreshBase} attached to this ListFragment.
     */
    public final PullToRefreshBase<ScrollView> getPullToRefreshListView() {
        return mPullToRefreshListView;
    }

    public void setRefreshing() {
        getPullToRefreshListView().setRefreshing();
    }

    public void setRefreshing(boolean doScroll) {
        getPullToRefreshListView().setRefreshing(doScroll);
    }

    public boolean isRefreshing() {
        return getPullToRefreshListView().isRefreshing();
    }

    public void onRefreshComplete() {
        getPullToRefreshListView().onRefreshComplete();
    }

    @Override
    public void onDestroyView() {
        mHandler.removeCallbacks(mRequestRefresh);
        mPullToRefreshListView = null;

        super.onDestroyView();
    }
}
