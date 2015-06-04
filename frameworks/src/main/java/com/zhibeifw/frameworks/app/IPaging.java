package com.zhibeifw.frameworks.app;

/**
 * Created by Administrator on 2015/6/1 0001.
 */
public interface IPaging {
    void setIsLoading(boolean isLoading);

    boolean isLoading();

    void setHasMoreItems(boolean hasMoreItems);

    boolean hasMoreItems();
}
