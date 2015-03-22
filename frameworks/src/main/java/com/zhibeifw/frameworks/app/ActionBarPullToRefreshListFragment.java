package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by Administrator on 2015/3/22 0022.
 */
public class ActionBarPullToRefreshListFragment extends ListFragment
    implements OnRefreshListener {

    private PullToRefreshLayout mPullToRefreshLayout;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        ViewGroup viewGroup = (ViewGroup) view;

        // As we're using a ListFragment we create a PullToRefreshLayout manually
        mPullToRefreshLayout = new PullToRefreshLayout(viewGroup.getContext());

        // We can now setup the PullToRefreshLayout
        ActionBarPullToRefresh.from(getActivity())
            // We need to insert the PullToRefreshLayout into the Fragment's ViewGroup
            .insertLayoutInto(viewGroup)
            // Here we mark just the ListView and it's Empty View as pullable
            .theseChildrenArePullable(android.R.id.list, android.R.id.empty)
            .listener(this)
            .setup(mPullToRefreshLayout);
    }

    @Override
    public void onRefreshStarted(View view) {

    }

    public PullToRefreshLayout getPullToRefreshLayout() {
        return mPullToRefreshLayout;
    }

    public void setRefreshComplete() {
        getPullToRefreshLayout().setRefreshComplete();
    }

    public boolean isRefreshing() {
        return getPullToRefreshLayout().isRefreshing();
    }

    public void setRefreshing(boolean refreshing) {
        getPullToRefreshLayout().setRefreshing(refreshing);
    }
}
