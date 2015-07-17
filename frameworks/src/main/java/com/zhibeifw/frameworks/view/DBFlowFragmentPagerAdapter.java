package com.zhibeifw.frameworks.view;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.Model;
import com.zhibeifw.frameworks.dbflow.DBFlowCursorLoader;
import com.zhibeifw.frameworks.dbflow.ModelUtils;

/**
 * Created by Administrator on 2015/7/16 0016.
 */
public abstract class DBFlowFragmentPagerAdapter<ModelClass extends Model> extends ArrayFragmentPagerAdapter<ModelClass> implements LoaderManager.LoaderCallbacks<Cursor> {

    private final Context mContext;

    private ModelQueriable<ModelClass> modelQueriable;

    public DBFlowFragmentPagerAdapter(FragmentActivity activity, Class<ModelClass> table) {
        this(activity, new Select().from(table));
    }

    public DBFlowFragmentPagerAdapter(FragmentActivity activity, ModelQueriable<ModelClass> modelQueriable) {
        super(activity.getSupportFragmentManager());
        this.modelQueriable = modelQueriable;
        mContext = activity;
        activity.getSupportLoaderManager().initLoader(modelQueriable.hashCode(), null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new DBFlowCursorLoader<ModelClass>(mContext, modelQueriable);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        replaceAll(ModelUtils.convertToList(data, modelQueriable.getTable()));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        clear();
    }
}
