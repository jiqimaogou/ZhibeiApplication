package com.zhibeifw.frameworks.widget;

import android.content.Context;

import com.joanzapata.android.QuickAdapter;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.runtime.FlowContentObserver;
import com.raizlabs.android.dbflow.structure.Model;

import java.util.List;

/**
 * Created by Administrator on 2015/3/22 0022.
 */
public abstract class FlowQuickAdapter<ModelClass extends Model>  extends QuickAdapter {

    /**
     * Holds the table cursor
     */
    private FlowCursorList<ModelClass> mCursorList;

    private FlowContentObserver mFlowContentObserver = new FlowContentObserver();

    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    protected FlowQuickAdapter(Context context, int layoutResId, FlowCursorList<ModelClass> mCursorList) {
        super(context, layoutResId, mCursorList.getAll());
        setCursorList(mCursorList);
        FlowContentObserver.ModelChangeListener modelChangeListener = new FlowContentObserver.ModelChangeListener() {
            @Override
            public void onModelChanged() {
                refresh();
            }

            @Override
            public void onModelSaved() {
                refresh();
            }

            @Override
            public void onModelDeleted() {
                refresh();
            }

            @Override
            public void onModelInserted() {
                refresh();
            }

            @Override
            public void onModelUpdated() {
                refresh();
            }
        };

        mFlowContentObserver.addModelChangeListener(modelChangeListener);
    }

    public void setCursorList(FlowCursorList<ModelClass> mCursorList) {
        this.mCursorList = mCursorList;
        mFlowContentObserver.unregisterForContentChanges(context);
        mFlowContentObserver.registerForContentChanges(context, mCursorList.getTable());
    }

    public List<ModelClass> getAll() {
        return mCursorList.getAll();
    }

    public void refresh() {
        replaceAll(getAll());
    }
}
