package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paging.listview.PagingListView;
import com.zhibeifw.frameworks.R;

/**
 * Created by Administrator on 2015/3/22 0022.
 */
public class FloatingActionButtonListFragment extends ActionBarPullToRefreshListFragment implements PagingListView.Pagingable {

    public PagingListView getPagingListView() {
        return (PagingListView) getListView();
    }

    // @Override
    public View onCreateListView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.floatingactionbutton, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPagingListView().setHasMoreItems(true);
        getPagingListView().setPagingableListener(this);
    }

    @Override
    public void onLoadMoreItems() {

    }
}
