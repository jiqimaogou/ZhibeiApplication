package com.zhibeifw.frameworks.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/30 0030.
 */
public class DBFlowModelListRequest<ModelClass extends Model> extends GsonRequest<List<ModelClass>> {
    public DBFlowModelListRequest(int method, String url, Gson gson, Type type, Map<String, String> headers,
            Map<String, String> params, Response.Listener<List<ModelClass>> listener,
            Response.ErrorListener errorListener) {
        super(method, url, gson, type, headers, params, listener, errorListener);
    }

    public DBFlowModelListRequest(String url, Gson gson, Type type, Map<String, String> headers,
            Response.Listener<List<ModelClass>> listener, Response.ErrorListener errorListener) {
        super(url, gson, type, headers, listener, errorListener);
    }

    @Override
    protected Response<List<ModelClass>> parseNetworkResponse(NetworkResponse response) {
        Response<List<ModelClass>> networkResponse = super.parseNetworkResponse(response);
        List<ModelClass> result = networkResponse.result;
        for (ModelClass modelClass : result) {
            modelClass.save();
        }
        SqlUtils.notifyModelChanged(result.get(0).getClass(), BaseModel.Action.INSERT);
        return networkResponse;
    }

}
