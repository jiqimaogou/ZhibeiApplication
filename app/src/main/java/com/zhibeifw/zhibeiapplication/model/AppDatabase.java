package com.zhibeifw.zhibeiapplication.model;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, foreignKeysSupported = true)
public class AppDatabase {

    public static final String NAME = "ZhibeiApp";

    public static final int VERSION = 1;
}
