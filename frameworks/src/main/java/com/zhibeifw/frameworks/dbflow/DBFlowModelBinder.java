package com.zhibeifw.frameworks.dbflow;

import android.database.Cursor;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.ListAdapter;

import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.Model;
import com.zhibeifw.frameworks.app.ListFragment;
import com.zhibeifw.frameworks.binding.BinderAdapter;
import com.zhibeifw.frameworks.binding.CollectionAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/5/29 0029.
 */
public class DBFlowModelBinder {
    public static <ModelClass extends Model, T extends ViewDataBinding> T bind(final FragmentActivity fragmentActivity,
            final ModelQueriable<ModelClass> modelQueriable) {
        final Class<ModelClass> modelClass = modelQueriable.getTable();
        fragmentActivity.getSupportLoaderManager()
                        .initLoader(modelQueriable.hashCode(), null, new LoaderManager.LoaderCallbacks<Cursor>() {
                            @Override
                            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                                return new DBFlowCursorLoader<ModelClass>(fragmentActivity, modelQueriable);
                            }

                            @Override
                            public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                                ModelClass retModel = ModelUtils.convertToModel(cursor, modelClass);

                            }


                            @Override
                            public void onLoaderReset(Loader<Cursor> loader) {

                            }
                        });

        return null;
    }

    public static <ModelClass extends Model, T extends ViewDataBinding> void bind(final ListFragment listFragment,
            final ModelQueriable<ModelClass> modelQueriable, final int itemResource) {
        final Class<ModelClass> modelClass = modelQueriable.getTable();
        final FragmentActivity fragmentActivity = listFragment.getActivity();
        fragmentActivity.getSupportLoaderManager()
                        .initLoader(modelQueriable.hashCode(), null, new LoaderManager.LoaderCallbacks<Cursor>() {
                            @Override
                            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                                return new DBFlowCursorLoader<ModelClass>(fragmentActivity, modelQueriable);
                            }

                            @Override
                            public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                                List<ModelClass> list = ModelUtils.convertToList(cursor, modelClass);
                                ListAdapter adapter = listFragment.getListAdapter();
                                if (adapter == null || !(adapter instanceof CollectionAdapter)) {
                                    adapter = new BinderAdapter<ModelClass>(fragmentActivity, itemResource, list);
                                    listFragment.setListAdapter(adapter);
                                } else {
                                    BinderAdapter<ModelClass> modelArrayAdapter = (BinderAdapter<ModelClass>) adapter;
                                    modelArrayAdapter.clear();
                                    modelArrayAdapter.addAll(list);
                                }
                            }


                            @Override
                            public void onLoaderReset(Loader<Cursor> loader) {
                                listFragment.setListAdapter(null);
                            }
                        });

    }
}
