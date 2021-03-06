package com.zhibeifw.frameworks.rx.observers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import rx.Subscriber;

/**
 * Created by Administrator on 2015/4/11 0011.
 */
public class ProgressDialogSubscriber<T> extends Subscriber<T> { 
    private final Context mContext;
    private ProgressDialog mProgressDialog;

    public ProgressDialogSubscriber(Context context) {
        this.mContext = context;
    }

    public ProgressDialogSubscriber(Context context, Subscriber<?> op) {
        super(op);
        this.mContext = context;
    }

    @Override
    public void onStart() {
        getProgressDialog().show();
        super.onStart();
    }

    @Override
    public void onCompleted() {
        getProgressDialog().dismiss();
    }

    @Override
    public void onError(Throwable e) {
        getProgressDialog().dismiss();
    }

    @Override
    public void onNext(T t) {

    }

    public ProgressDialog getProgressDialog() {
        synchronized (this) {
            if (mProgressDialog != null) {
                return mProgressDialog;
            }

            mProgressDialog = onCreateDialog(mContext);
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    unsubscribe();
                    mProgressDialog.dismiss();
                }
            });
            return mProgressDialog;
        }
    }

    public static ProgressDialog onCreateDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("网络连接中...");
        progressDialog.setIndeterminate(true);
        return progressDialog;
    }
}
