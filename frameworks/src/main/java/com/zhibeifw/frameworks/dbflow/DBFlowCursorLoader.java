package com.zhibeifw.frameworks.dbflow;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;

import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public class DBFlowCursorLoader<ModelClass extends Model> extends AsyncTaskLoader<Cursor> {
    ContentResolver mContentResolver;

    final ForceLoadContentObserver mObserver;

    ModelQueriable<ModelClass> mModelQueriable;
    Uri mUri;

    Cursor mCursor;

    /* Runs on a worker thread */
    @Override
    public Cursor loadInBackground() {
        Cursor cursor = mModelQueriable.query();
        if (cursor != null) {
            // Ensure the cursor window is filled
            cursor.getCount();
            cursor.setNotificationUri(mContentResolver, mUri);
            cursor.registerContentObserver(mObserver);
        }
        return cursor;
    }

    /* Runs on the UI thread */
    @Override
    public void deliverResult(Cursor cursor) {
        if (isReset()) {
            // An async query came in while the loader is stopped
            if (cursor != null) {
                cursor.close();
            }
            return;
        }
        Cursor oldCursor = mCursor;
        mCursor = cursor;

        if (isStarted()) {
            super.deliverResult(cursor);
        }

        if (oldCursor != null && oldCursor != cursor && !oldCursor.isClosed()) {
            oldCursor.close();
        }
    }

    /**
     * Creates an empty unspecified DBFlowCursorLoader.  You must follow this with
     * calls to {@link #setModelQueriable(ModelQueriable)}
     * to specify the query to perform.
     */
    public DBFlowCursorLoader(Context context) {
        super(context);
        mContentResolver = getContext().getContentResolver();
        mObserver = new ForceLoadContentObserver();
    }

    /**
     * Creates a fully-specified DBFlowCursorLoader.  See
     * {@link android.content.ContentResolver#query(Uri, String[], String, String[], String)
     * ContentResolver.query()} for documentation on the meaning of the
     * parameters.  These will be passed as-is to that call.
     */
    public DBFlowCursorLoader(Context context, ModelQueriable<ModelClass> modelQueriable) {
        super(context);
        mContentResolver = context.getContentResolver();
        mObserver = new ForceLoadContentObserver();
        setModelQueriable(modelQueriable);
    }

    /**
     * Starts an asynchronous load of the contacts list data. When the result is ready the callbacks
     * will be called on the UI thread. If a previous load has been completed and is still valid
     * the result may be passed to the callbacks immediately.
     * <p/>
     * Must be called from the UI thread
     */
    @Override
    protected void onStartLoading() {
        if (mCursor != null) {
            deliverResult(mCursor);
        }
        if (takeContentChanged() || mCursor == null) {
            forceLoad();
        }
    }

    /**
     * Must be called from the UI thread
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override
    public void onCanceled(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
        mCursor = null;
    }

    public ModelQueriable<ModelClass> getModelQueriable() {
        return mModelQueriable;
    }

    public void setModelQueriable(ModelQueriable<ModelClass> modelQueriable) {
        mModelQueriable = modelQueriable;
        mUri = SqlUtils.getNotificationUri(mModelQueriable.getTable(), BaseModel.Action.CHANGE);
    }

    @Override
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(prefix, fd, writer, args);
        writer.print(prefix);
        writer.print("mModelQueriable=");
        writer.println(mModelQueriable);
    }
}
