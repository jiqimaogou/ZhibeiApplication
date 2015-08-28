package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.zhibeifw.frameworks.R;

/**
 * Created by Administrator on 2015/7/10 0010.
 */
public class PullToRefreshListFragment extends ListFragment implements PullToRefreshBase.OnRefreshListener2 {

    private PullToRefreshBase<AbsListView> mPullToRefreshListView;

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
        mHandler.postDelayed(mRequestRefresh, 500);
    }

    @Override
    public View onCreateListView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPullToRefreshListView = (PullToRefreshBase) inflater.inflate(R.layout.ptr_list, container, false);

        // Set a listener to be invoked when the list should be refreshed.
        mPullToRefreshListView.setOnRefreshListener(this);

        return mPullToRefreshListView;
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
    public final PullToRefreshBase<AbsListView> getPullToRefreshListView() {
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
        mList = null;

        super.onDestroyView();
    }
}
