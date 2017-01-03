package com.example.luoyican.myapp.model;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;

import com.example.luoyican.myapp.BuildConfig;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luoyican on 2016/12/28.
 */
public class ModelManager {
    private static ModelManager modelManager;
    private Context appContext;
    private Handler mainHalder;
    private ExecutorService threadPool;
    private boolean isDebug = BuildConfig.DEBUG;

    public static ModelManager getInstance() {
        if (modelManager == null) {
            modelManager = new ModelManager();
        }
        return modelManager;
    }

    public void init(Context appContext) {
        this.appContext = appContext;
        mainHalder = new Handler();
        threadPool = Executors.newFixedThreadPool(4);
        initImageLoader(appContext);
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public Handler getMainHalder() {
        return mainHalder;
    }

    public Context getAppContext() {
        return appContext;
    }

    /**
     * 初始化ImageLoader
     *
     * @param context
     */
    private void initImageLoader(Context context) {
        int MEN_CACHE_SIZE = 1024 * 1024 *((ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass() / 8;

    }
}
