package com.zhibeifw.frameworks.wasp;

import com.android.volley.Request;
import com.orhanobut.wasp.CallBack;
import com.orhanobut.wasp.WaspError;

/**
 * Created by Administrator on 2015/7/11 0011.
 */
public class CallBackDecorator<T> implements CallBack<T> {

    private CallBack<T> mCallback;

    public CallBackDecorator() {
    }

    public CallBackDecorator(CallBack<T> callBack) {
        this.mCallback = callBack;
    }

    @Override
    public void onStart(Request<T> request) {
        if (mCallback != null) {
            mCallback.onStart(request);
        }
    }

    @Override
    public void onSuccess(T t) {
        if (mCallback != null) {
            mCallback.onSuccess(t);
        }
    }

    @Override
    public void onError(WaspError error) {
        if (mCallback != null) {
            mCallback.onError(error);
        }
    }
}
