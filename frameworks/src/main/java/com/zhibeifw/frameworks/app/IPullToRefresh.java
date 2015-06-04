package com.zhibeifw.frameworks.app;

/**
 * Created by Administrator on 2015/6/1 0001.
 */
public interface IPullToRefresh {
    void setRefreshComplete();

    boolean isRefreshing();

    void setRefreshing(boolean refreshing);
}
