package com.zhibeifw.frameworks.volley;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.zhibeifw.frameworks.R;

/**
 * Created by Administrator on 2015/6/29 0029.
 */
public class ProgressDialogListener<T> implements VolleyListener<T> {

    private ProgressDialog mProgressDialog;

    private final Context mContext;

    public ProgressDialogListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        hideDialog();
    }

    @Override
    public void onResponse(T response) {
        hideDialog();
    }

    @Override
    public void onStartRequest(Request request) {
        showDialog();
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
}
