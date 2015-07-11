package com.zhibeifw.frameworks.dbflow;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.Model;
import com.zhibeifw.frameworks.binding.BinderAdapter;

/**
 * Created by Administrator on 2015/6/10 0010.
 */
public class DBFlowAdapter<ModelClass extends Model> extends BinderAdapter<ModelClass> implements LoaderManager.LoaderCallbacks<Cursor> {

    private ModelQueriable<ModelClass> modelQueriable;

    public DBFlowAdapter(FragmentActivity activity, int resource, int variableId, Class<ModelClass> table) {
        this(activity, resource, variableId, new Select().from(table));
    }

    public DBFlowAdapter(FragmentActivity activity, int resource, int variableId,
            ModelQueriable<ModelClass> modelQueriable) {
        super(activity, resource, variableId);
        this.modelQueriable = modelQueriable;
        activity.getSupportLoaderManager().initLoader(modelQueriable.hashCode(), null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new DBFlowCursorLoader<ModelClass>(getContext(), modelQueriable);
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
