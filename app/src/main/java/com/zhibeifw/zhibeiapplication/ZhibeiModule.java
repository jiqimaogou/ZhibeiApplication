package com.zhibeifw.zhibeiapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.zhibeifw.frameworks.app.ActionBarPullToRefreshListFragment;
import com.zhibeifw.frameworks.binding.BinderAdapter;
import com.zhibeifw.frameworks.dbflow.DBFlowCursorLoader;
import com.zhibeifw.frameworks.dbflow.ModelUtils;
import com.zhibeifw.frameworks.volley.DBFlowModelListRequest;
import com.zhibeifw.zhibeiapplication.syllabus.OnlineRoom;
import com.zhibeifw.zhibeiapplication.syllabus.OnlineRoomListFragment;

import java.lang.reflect.Type;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2015/5/30 0030.
 */
@Module
public class ZhibeiModule {
    ActionBarPullToRefreshListFragment actionBarPullToRefreshListFragment;

    public ZhibeiModule(OnlineRoomListFragment onlineRoomListFragment) {
        this.actionBarPullToRefreshListFragment = onlineRoomListFragment;
    }

    @Provides
    ActionBarPullToRefreshListFragment provideActionBarPullToRefreshListFragment() {
        return actionBarPullToRefreshListFragment;
    }

    @Provides
    LoaderManager provideLoaderManager(ActionBarPullToRefreshListFragment actionBarPullToRefreshListFragment) {
        return actionBarPullToRefreshListFragment.getActivity().getSupportLoaderManager();
    }

    @Provides
    Loader<Cursor> provideLoader(final LoaderManager loaderManager,
            final ActionBarPullToRefreshListFragment actionBarPullToRefreshListFragment,
            final ModelQueriable<OnlineRoom> modelQueriable, final int itemResource) {
        return loaderManager.initLoader(modelQueriable.hashCode(), null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                return new DBFlowCursorLoader<OnlineRoom>(actionBarPullToRefreshListFragment.getActivity(),
                                                          modelQueriable);
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                List<OnlineRoom> list = ModelUtils.convertToList(cursor, modelQueriable.getTable());
                ListAdapter adapter = actionBarPullToRefreshListFragment.getListAdapter();
                if (adapter == null || !(adapter instanceof BinderAdapter)) {
                    adapter = new BinderAdapter<OnlineRoom>(actionBarPullToRefreshListFragment.getActivity(),
                                                            itemResource,
                                                            list);
                    actionBarPullToRefreshListFragment.setListAdapter(adapter);
                } else {
                    BinderAdapter<OnlineRoom> modelArrayAdapter = (BinderAdapter<OnlineRoom>) adapter;
                    modelArrayAdapter.clear();
                    modelArrayAdapter.addAll(list);
                }
                actionBarPullToRefreshListFragment.setRefreshComplete();
            }


            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                actionBarPullToRefreshListFragment.setListAdapter(null);
            }
        });
    }


    @Provides
    Request provideRequest(Gson gson, RequestQueue requestQueue) {
        Type type = new TypeToken<List<OnlineRoom>>() {
        }.getType();
        DBFlowModelListRequest<OnlineRoom> request = new DBFlowModelListRequest<OnlineRoom>(
                "http://rocky-brook-9618.herokuapp.com/online_rooms.json",
                gson,
                type,
                null,
                null,
                null);
        return requestQueue.add(request);
    }

    @Provides
    ModelQueriable<OnlineRoom> provideModelQueriable() {
        return new Select().from(OnlineRoom.class);
    }

    @Provides
    int provideResource() {
        return R.layout.syllabus_online_room_item_layout;
    }

    @Provides
    BaseExpandableListAdapter provideExpandableListAdapter() {
        return new BaseExpandableListAdapter() {
            private List<String> GroupData;//定义组数据
            private List<List<String>> ChildrenData;//定义组中的子数据

            @Override
            public int getGroupCount() {
                return 0;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return 0;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return null;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return null;
            }

            @Override
            public long getGroupId(int groupPosition) {
                return 0;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                    ViewGroup parent) {
                return null;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return false;
            }
        };
    }

}
