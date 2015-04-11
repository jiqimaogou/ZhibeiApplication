package com.zhibeifw.frameworks.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.raizlabs.android.dbflow.list.FlowTableList;
import com.raizlabs.android.dbflow.runtime.FlowContentObserver;
import com.raizlabs.android.dbflow.structure.Model;

/**
 * Created by Administrator on 2015/4/11 0011.
 */
public abstract class FlowQuickFragmentPagerAdapter<ModelClass extends Model> extends FragmentPagerAdapter {
    private final Context mContext;

    /**
     * Holds the table cursor
     */
    private FlowTableList<ModelClass> mFlowTableList;

    private FlowContentObserver mFlowContentObserver = new FlowContentObserver();

    private Runnable notifyDataSetChangedAction = new Runnable() {
        @Override
        public void run() {
            notifyDataSetChanged();
        }
    };

    public FlowQuickFragmentPagerAdapter(Context mContext, FragmentManager fm, FlowTableList<ModelClass> flowTableList) {
        super(fm);
        this.mContext = mContext;
        this.mFlowTableList = flowTableList;
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

    @Override
    public int getCount() {
        return mFlowTableList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getPageTitle(mFlowTableList.get(position));
    }

    public abstract CharSequence getPageTitle(ModelClass model);

    public void setFlowTableList(FlowTableList<ModelClass> flowTableList) {
        this.mFlowTableList = flowTableList;
        mFlowContentObserver.unregisterForContentChanges(mContext);
        mFlowContentObserver.registerForContentChanges(mContext, flowTableList.getCursorList().getTable());
        refresh();
    }

    public void refresh() {
        ((Activity) mContext).runOnUiThread(notifyDataSetChangedAction);
    }

}
