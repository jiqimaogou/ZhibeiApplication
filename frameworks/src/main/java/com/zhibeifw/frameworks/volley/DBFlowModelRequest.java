package com.zhibeifw.frameworks.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.request.GsonRequest;
import com.raizlabs.android.dbflow.structure.Model;

import java.util.Map;

/**
 * Created by Administrator on 2015/5/30 0030.
 */
public class DBFlowModelRequest<ModelClass extends Model> extends GsonRequest<ModelClass> {
    public DBFlowModelRequest(String url, Class<ModelClass> clazz, Map<String, String> headers,
            Response.Listener<ModelClass> listener, Response.ErrorListener errorListener) {
        super(url, clazz, headers, listener, errorListener);
    }

    public DBFlowModelRequest(int type, String url, Class<ModelClass> clazz, Map<String, String> headers,
            Map<String, String> params, Response.Listener<ModelClass> listener, Response.ErrorListener errorListener) {
        super(type, url, clazz, headers, params, listener, errorListener);
    }

    @Override
    protected Response<ModelClass> parseNetworkResponse(NetworkResponse response) {
        Response<ModelClass> networkResponse = super.parseNetworkResponse(response);
        networkResponse.result.save();
        return networkResponse;
    }
}
