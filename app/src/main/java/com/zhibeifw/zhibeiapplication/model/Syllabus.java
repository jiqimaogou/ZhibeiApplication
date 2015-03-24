package com.zhibeifw.zhibeiapplication.model;

import com.google.gson.annotations.Expose;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ContainerAdapter;
import com.raizlabs.android.dbflow.annotation.ContainerKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Administrator on 2015/3/22 0022.
 */
@Table
@ContainerAdapter
public class Syllabus extends BaseModel {

    @Expose
    @ContainerKey("_id")
    @Column(columnType = Column.PRIMARY_KEY_AUTO_INCREMENT)
    long id;

    @Expose
    @Column
    String syllabus;

    @Expose
    @Column
    String push_content;

    @Expose
    @Column
    long updated_at;

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getPush_content() {
        return push_content;
    }

    public void setPush_content(String push_content) {
        this.push_content = push_content;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }
}
