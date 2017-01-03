package com.example.luoyican.myapp.ui.contact;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luoyican.myapp.R;
import com.example.luoyican.myapp.utils.LogUtil;
import com.example.luoyican.myapp.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by luoyican on 2016/12/28.
 */
public class ShowContactFragment extends Fragment {
    private ListView listview;
    private HashMap<String, ContactMsgModel> map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_contact, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listview = (ListView) getView().findViewById(R.id.listview);
        map = GetContactResult.getInstance().getContact(getActivity());
        init();
        initView();
    }

    private void init() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
                Adapter container = view.getAdapter();
                ContactMsgModel contacnt = (ContactMsgModel) container.getItem(i);
                try {
                    makeCall(contacnt.getPhone());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        List<ContactMsgModel> list = new ArrayList<>();
        for (Iterator it = map.keySet().iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            list.add(map.get(key));
            LogUtil.e("d", key);
        }

        //去掉空串
        for (int i = 0; i < list.size(); i++) {
            try {
                LogUtil.e(list.get(i).getName() + ":" + list.get(i).getPhone() + "\n");
            } catch (Exception e) {
                list.remove(i);
                LogUtil.e("null");
            }
        }
        Collections.sort(list, new ComparatorUser());//list排序

        ContactAdapter contactAdapter = new ContactAdapter(list);
        listview.setAdapter(contactAdapter);
    }

    public void makeCall(String number) {
        try {
            //Intent.ACTION_DIAL 并不直接打电话 Intent.ACTION_CALL 直接打电话
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            ToastUtil.show("拨打号码失败," + number);
        }
    }

}

/*
* list排序
* */
class ComparatorUser implements Comparator {
    public int compare(Object o1, Object o2) {
        ContactMsgModel c1 = (ContactMsgModel) o1;
        ContactMsgModel c2 = (ContactMsgModel) o2;
        Collator instance = Collator.getInstance(Locale.CHINA);
        int flag = instance.compare(c1.getName(), c2.getName());
        if (flag == 0) {
            return c1.getPhone().compareTo(c2.getPhone());
        }
        return flag;
    }

}

