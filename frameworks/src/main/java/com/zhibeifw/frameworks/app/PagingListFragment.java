package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.paging.listview.PagingListView;

/**
 * Created by Administrator on 2015/3/27 0027.
 */
public class PagingListFragment extends ListFragment implements PagingListView.Pagingable {
    public PagingListView getPagingListView() {
        return (PagingListView) getListView();
    }

    @Override
    public View onCreateListView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ListView lv = new PagingListView(getActivity());
        lv.setId(android.R.id.list);
        lv.setDrawSelectorOnTop(false);
        
        return lv;
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
