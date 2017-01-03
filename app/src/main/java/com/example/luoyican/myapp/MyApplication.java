package com.example.luoyican.myapp;

import android.app.Application;
import android.content.Context;

import com.example.luoyican.myapp.model.ModelManager;

/**
 * Created by luoyican on 2016/12/29.
 */
public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ModelManager.getInstance().init(this);
        ModelManager.getInstance().setDebug(BuildConfig.DEBUG);
    }
}
