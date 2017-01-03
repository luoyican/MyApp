package com.example.luoyican.myapp.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.luoyican.myapp.model.ModelManager;

/**
 * Created by luoyican on 2016/12/29.
 */
public class ToastUtil {
    private static Toast toast;

    private static void instanceToast(){
        if(toast == null){
            toast = Toast.makeText(ModelManager.getInstance().getAppContext(), "", Toast.LENGTH_LONG);
        }
    }

    public static void show(CharSequence charSequence){
        if (TextUtils.isEmpty(charSequence)) return;
        instanceToast();
        toast.setText(charSequence);
        toast.show();
    }
}
