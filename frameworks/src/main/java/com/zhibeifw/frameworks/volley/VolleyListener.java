package com.zhibeifw.frameworks.volley;

import com.android.volley.Response;

/**
 * Created by Administrator on 2015/6/26 0026.
 */
public interface VolleyListener<T> extends BaseRequest.StartListener, Response.Listener<T>, Response.ErrorListener {

}
