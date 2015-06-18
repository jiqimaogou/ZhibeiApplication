package com.zhibeifw.frameworks.binding;

import android.widget.ListAdapter;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2015/6/5 0005.
 */
public interface CollectionAdapter<T> extends ListAdapter {
    void add(T object);

    void addAll(Collection<? extends T> collection);

    void addAll(T... items);

    void insert(T object, int index);

    void remove(T object);

    void clear();

    void replaceAll(List<T> elem);
}
