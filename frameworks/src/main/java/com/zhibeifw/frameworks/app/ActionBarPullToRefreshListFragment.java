package com.zhibeifw.frameworks.app;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by Administrator on 2015/3/22 0022.
 */
public class ActionBarPullToRefreshListFragment extends PagingListFragment implements OnRefreshListener, IPullToRefresh {

    private PullToRefreshLayout mPullToRefreshLayout;

    DataSetObserver mDataSetObserver;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup viewGroup = (ViewGroup) view;

        // As we're using a ViewPagerFragment we create a PullToRefreshLayout manually
        mPullToRefreshLayout = new PullToRefreshLayout(viewGroup.getContext());

        // We can now setup the PullToRefreshLayout
        ActionBarPullToRefresh.from(getActivity())
                // We need to insert the PullToRefreshLayout into the Fragment's ViewGroup
                .insertLayoutInto(viewGroup)
                        // Here we mark just the ListView and it's Empty View as pullable
                .theseChildrenArePullable(android.R.id.list, android.R.id.empty)
                .listener(this)
                .setup(mPullToRefreshLayout);

        if (mAdapter != null && mDataSetObserver == null) {
            mDataSetObserver = new MyDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
    }

    @Override
    public void onDestroyView() {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
            mDataSetObserver = null;
        }

        super.onDestroyView();
    }

    @Override
    public void onRefreshStarted(View view) {

    }

    @Override
    public void setRefreshComplete() {
        getPullToRefreshLayout().setRefreshComplete();
    }

    @Override
    public boolean isRefreshing() {
        return getPullToRefreshLayout().isRefreshing();
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        getPullToRefreshLayout().setRefreshing(refreshing);
    }

    public PullToRefreshLayout getPullToRefreshLayout() {
        return mPullToRefreshLayout;
    }

    @Override
    public void setListAdapter(ListAdapter adapter) {
        if (null != mAdapter) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }

        if (adapter != null) {
            mDataSetObserver = new MyDataSetObserver();
            adapter.registerDataSetObserver(mDataSetObserver);
        }

        super.setListAdapter(adapter);
    }

    private class MyDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            setRefreshComplete();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            setRefreshComplete();
        }
    }
}
