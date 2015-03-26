package com.zhibeifw.frameworks.widget;

import android.app.Activity;
import android.content.Context;

import com.joanzapata.android.QuickAdapter;
import com.raizlabs.android.dbflow.list.FlowTableList;
import com.raizlabs.android.dbflow.runtime.FlowContentObserver;
import com.raizlabs.android.dbflow.structure.Model;

/**
 * Created by Administrator on 2015/3/22 0022.
 */
public abstract class FlowQuickAdapter<ModelClass extends Model>  extends QuickAdapter<ModelClass> {

    /**
     * Holds the table cursor
     */
    private FlowTableList<ModelClass> mFlowTableList;

    private FlowContentObserver mFlowContentObserver = new FlowContentObserver();

    private Runnable replaceAllAction = new Runnable() {
        @Override
        public void run() {
            replaceAll(mFlowTableList.getAll());
        }
    };


    /**
     * Create a QuickAdapter.
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    protected FlowQuickAdapter(Context context, int layoutResId, FlowTableList<ModelClass> flowTableList) {
        super(context, layoutResId);
        setFlowTableList(flowTableList);
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

    public void setFlowTableList(FlowTableList<ModelClass> flowTableList) {
        this.mFlowTableList = flowTableList;
        mFlowContentObserver.unregisterForContentChanges(context);
        mFlowContentObserver.registerForContentChanges(context, flowTableList.getCursorList().getTable());
        refresh();
    }

    public void refresh() {
        ((Activity) context).runOnUiThread(replaceAllAction);
    }
}
