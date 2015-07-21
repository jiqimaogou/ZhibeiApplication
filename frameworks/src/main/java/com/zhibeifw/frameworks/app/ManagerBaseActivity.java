package com.zhibeifw.frameworks.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yayandroid.theactivitymanager.TheActivityManager;

public class ManagerBaseActivity extends AppCompatActivity {

    public ManagerBaseActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TheActivityManager.getInstance().onCreate(this);
    }

    protected void onResume() {
        super.onResume();
        TheActivityManager.getInstance().onResume(this);
    }

    protected void onPause() {
        super.onPause();
        TheActivityManager.getInstance().onPause(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        TheActivityManager.getInstance().onDestroy(this);
    }
}
