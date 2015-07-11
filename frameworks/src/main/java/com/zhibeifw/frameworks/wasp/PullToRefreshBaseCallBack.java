package com.zhibeifw.frameworks.wasp;

import com.android.volley.Request;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.orhanobut.wasp.CallBack;
import com.orhanobut.wasp.WaspError;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2015/7/10 0010.
 */
public class PullToRefreshBaseCallBack<T> extends CallBackDecorator<T> {

    private final WeakReference<PullToRefreshBase> mPullToRefreshBase;

    public PullToRefreshBaseCallBack(PullToRefreshBase pullToRefreshBase) {
        mPullToRefreshBase = new WeakReference<PullToRefreshBase>(pullToRefreshBase);
    }

    public PullToRefreshBaseCallBack(CallBack<T> callBack, PullToRefreshBase pullToRefreshBase) {
        super(callBack);
        mPullToRefreshBase = new WeakReference<PullToRefreshBase>(pullToRefreshBase);
    }

    @Override
    public void onStart(Request<T> request) {
        super.onStart(request);
    }

    @Override
    public void onSuccess(T t) {
        super.onSuccess(t);
        onRefreshComplete();
    }

    @Override
    public void onError(WaspError error) {
        super.onError(error);
        onRefreshComplete();
    }

    private void onRefreshComplete() {
        final PullToRefreshBase pullToRefreshBase = mPullToRefreshBase.get();
        if (pullToRefreshBase != null) {
            pullToRefreshBase.onRefreshComplete();
        }
    }
}
