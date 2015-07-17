package com.zhibeifw.zhibeiapplication.syllabus;

import com.google.gson.annotations.Expose;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.cache.BaseCacheableModel;
import com.zhibeifw.zhibeiapplication.model.AppDatabase;

/**
 * Created by Administrator on 2015/7/16 0016.
 */

@Table(databaseName = AppDatabase.NAME)
public class OnlineRoomCategory extends BaseCacheableModel {

    @Column
    @PrimaryKey
    @Expose
    long id;

    @Column
    @Expose
    String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
