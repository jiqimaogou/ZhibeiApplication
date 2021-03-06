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
public class ProgressCallback<T> extends CallBackDecorator<T> {

    private ProgressDialog mProgressDialog;

    private final Context mContext;

    @Override
    public void onStart(Request<T> request) {
        showDialog();
        super.onStart(request);
    }

    @Override
    public void onError(WaspError error) {
        hideDialog();
        super.onError(error);
    }

    @Override
    public void onSuccess(T t) {
        hideDialog();
        super.onSuccess(t);
    }

    public ProgressCallback(Context context) {
        this.mContext = context;
    }

    public ProgressCallback(CallBack<T> callBack, Context context) {
        super(callBack);
        this.mContext = context;
    }

    protected void showDialog() {
        getProgressDialog().show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public ProgressDialog getProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = setProgressDialog();
        }
        return mProgressDialog;
    }

    protected ProgressDialog setProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle(mContext.getString(R.string.sending_request));
        progressDialog.setMessage(mContext.getString(R.string.sending_request));
        return progressDialog;
    }

    public Context getContext() {
        return mContext;
    }
}
