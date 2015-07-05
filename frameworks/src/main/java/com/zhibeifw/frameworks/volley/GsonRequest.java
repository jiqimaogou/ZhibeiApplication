package com.zhibeifw.frameworks.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

public class GsonRequest<T> extends JsonRequest<T> {

    private Gson gson;
    private final Type type;
    private final Map<String, String> headers;
    private final Map<String, String> params;

    /**
     * Make a request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param type    Relevant type object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(int method, String url, Gson gson, Object requestObject, Type type, Map<String, String> headers,
            Map<String, String> params, VolleyListener<T> listener) {
        super(method, url, gson.toJson(requestObject), listener, listener, listener);
        this.type = type;
        this.gson = gson;
        this.headers = headers;
        this.params = params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params != null ? params : super.getParams();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return (Response<T>) Response.success(gson.fromJson(json, type),
                                                  HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
