package com.zhibeifw.frameworks.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Authenticator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/26 0026.
 */
public abstract class BaseRequest<T> extends Request<T> {

    private static final String KEY_AUTH = "TOKEN";

    /**
     * Callback interface for delivering start requests.
     */
    public interface StartListener {

        /**
         * Callback method that an start has been occurred with the
         * provided Start code and optional user-readable message.
         */
        public void onStartRequest(Request request);
    }

    private final StartListener mStartListener;

    private Authenticator mAuthenticator;

    public BaseRequest(String url, StartListener startListener, Response.ErrorListener listener) {
        this(Method.DEPRECATED_GET_OR_POST, url, startListener, listener);
    }

    public BaseRequest(int method, String url, StartListener startListener, Response.ErrorListener listener) {
        super(method, url, listener);
        mStartListener = startListener;
    }

    @Override
    public Request<?> setRequestQueue(RequestQueue requestQueue) {
        deliverStart();
        return super.setRequestQueue(requestQueue);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (mAuthenticator != null) {
            Map<String, String> headers = new LinkedHashMap<>();
            headers.put(KEY_AUTH, mAuthenticator.getAuthToken());

            return headers;
        } else
            return super.getHeaders();
    }

    public void deliverStart() {
        if (mStartListener != null) {
            mStartListener.onStartRequest(this);
        }
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.mAuthenticator = authenticator;
    }
}
