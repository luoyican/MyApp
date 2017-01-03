package com.example.luoyican.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.luoyican.myapp.utils.LogUtil;

/**
 * Created by luoyican on 2016/12/28.
 */
public class BaseActivity extends FragmentActivity{
    private static final String TAG = "lyc_page";

    public BaseActivity getSelfActivity(){
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG,getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
