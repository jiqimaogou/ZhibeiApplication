package com.zhibeifw.zhibeiapplication.model;

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

    @ContainerKey("_id")
    @Column(columnType = Column.PRIMARY_KEY_AUTO_INCREMENT)
    long id;

    @Column
    String syllabus;

    @Column
    String push_content;

    @Column
    long updated_at;
}
