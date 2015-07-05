package com.zhibeifw.frameworks.wasp;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.orhanobut.wasp.CallBack;
import com.orhanobut.wasp.WaspError;
import com.zhibeifw.frameworks.R;

/**
 * Created by Administrator on 2015/6/29 0029.
 */
public class ProgressCallback<T> implements CallBack<T> {

    private ProgressDialog mProgressDialog;

    private final Context mContext;

    @Override
    public void onStart(Request<T> request) {
        showDialog();
    }

    @Override
    public void onSuccess(T t) {
        hideDialog();
    }

    @Override
    public void onError(WaspError error) {
        hideDialog();
    }

    public ProgressCallback(Context context) {
        this.mContext = context;
    }

    protected void showDialog() {
        if (mProgressDialog == null) {
            setProgressDialog();
        }
        mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void setProgressDialog() {
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle(mContext.getString(R.string.sending_request));
        mProgressDialog.setMessage(mContext.getString(R.string.sending_request));
    }

    public Context getContext() {
        return mContext;
    }
}
