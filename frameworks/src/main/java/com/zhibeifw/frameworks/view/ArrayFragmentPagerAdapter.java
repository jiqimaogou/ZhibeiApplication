package com.zhibeifw.frameworks.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/6/5 0005.
 */
public abstract class ArrayFragmentPagerAdapter<T> extends FragmentPagerAdapter {

    protected final List<T> data;

    public ArrayFragmentPagerAdapter(FragmentManager fm) {
        this(fm, new ArrayList<T>());
    }

    public ArrayFragmentPagerAdapter(FragmentManager fm, T... data) {
        this(fm, Arrays.asList(data));
    }

    public ArrayFragmentPagerAdapter(FragmentManager fm, List<T> data) {
        super(fm);
        this.data = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
    }

    @Override
    public Fragment getItem(int position) {
        return getItem(data.get(position));
    }

    public abstract Fragment getItem(T item);

    @Override
    public int getCount() {
        return data.size();
    }

    public void add(T elem) {
        data.add(elem);
        notifyDataSetChanged();
    }

    public void addAll(List<T> elem) {
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public void set(T oldElem, T newElem) {
        set(data.indexOf(oldElem), newElem);
    }

    public void set(int index, T elem) {
        data.set(index, elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        data.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        data.clear();
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return data.contains(elem);
    }

    /**
     * Clear data list
     */
    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }
}
