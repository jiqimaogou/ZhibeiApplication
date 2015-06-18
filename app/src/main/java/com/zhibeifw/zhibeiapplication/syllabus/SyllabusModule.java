package com.zhibeifw.zhibeiapplication.syllabus;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ListAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.zhibeifw.frameworks.dbflow.DBFlowAdapter;
import com.zhibeifw.frameworks.volley.DBFlowModelListRequest;
import com.zhibeifw.zhibeiapplication.R;

import java.lang.reflect.Type;
import java.util.List;


/**
 * Created by Administrator on 2015/5/30 0030.
 */
public class SyllabusModule extends DataModule {

    public SyllabusModule(Fragment fragment) {
        super(fragment);
    }

    @Override
    Request provideRequest(Gson gson, RequestQueue requestQueue) {
        Type type = new TypeToken<List<OnlineRoom>>() {
        }.getType();
        DBFlowModelListRequest<OnlineRoom> request = new DBFlowModelListRequest<OnlineRoom>(
                "http://rocky-brook-9618.herokuapp.com/online_rooms.json",
                gson,
                type,
                null,
                null,
                null);
        return requestQueue.add(request);
    }

    @Override
    ModelQueriable provideModelQueriable() {
        return new Select().from(OnlineRoom.class);
    }

    @Override
    ListAdapter provideListAdapter(FragmentActivity activity, ModelQueriable modelQueriable) {
        return new DBFlowAdapter(activity, R.layout.syllabus_online_room_item_layout, modelQueriable);
    }
}
