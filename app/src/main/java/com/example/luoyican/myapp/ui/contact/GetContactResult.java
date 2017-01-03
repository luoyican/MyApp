package com.example.luoyican.myapp.ui.contact;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by luoyican on 2016/12/28.
 */
public class GetContactResult {
    private static GetContactResult mGetContactModel;

    public static GetContactResult getInstance() {
        if (mGetContactModel == null) {
            mGetContactModel = new GetContactResult();
        }
        return mGetContactModel;
    }

    public HashMap<String, ContactMsgModel> getContact(Context context) {
        HashMap<String, ContactMsgModel> map ;
        //获取本地通讯录
        map = getContact(context, ContactsContract.CommonDataKinds.Phone.CONTENT_URI, "PHONE");
        //获取sim卡的方法，sim卡的uri有两种可能content://icc/adn与content://sim/adn
        map.putAll(getContact(context, Uri.parse("content://icc/adn"), "SIM"));
        map.putAll(getContact(context, Uri.parse("content://sim/adn"), "SIM"));

        HashMap<String, ContactMsgModel> result = new HashMap<>();
        Iterator it = map.keySet().iterator();
        while(it.hasNext()) {
            String key = it.next().toString().replace(" ","").trim() ;
            if(!result.containsKey(key)){
                result.put(key,map.get(key));
            }else {
                continue;
            }
        }
        return result;
    }

    private HashMap<String, ContactMsgModel> getContact(Context context, Uri uri, String from) {
        HashMap<String, ContactMsgModel> map = new HashMap<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor phoneCursor = resolver.query(uri,
                null,
                null,
                null,
                null);
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                String name;
                String phoneNumber;
                if ("PHONE".equals(from)) {
                    name = phoneCursor.getString(phoneCursor.
                            getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    phoneNumber = phoneCursor.getString(phoneCursor.
                            getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                } else {
                    name = phoneCursor.getString(phoneCursor.
                            getColumnIndex("name"));
                    phoneNumber = phoneCursor.getString(phoneCursor.
                            getColumnIndex("number"));
                }
                if (TextUtils.isEmpty(phoneNumber)) {
                    continue;
                }
                ContactMsgModel contactMsgModel = new ContactMsgModel();
                contactMsgModel.setName(name);
                contactMsgModel.setPhone(phoneNumber);
                contactMsgModel.setFrom(from);

                map.put(phoneNumber, contactMsgModel);
            }
            phoneCursor.close();
        }
        return map;
    }

}
