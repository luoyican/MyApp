package com.example.luoyican.myapp.ui.contact;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.luoyican.myapp.utils.ToastUtil;

import java.io.File;
import java.util.List;

/**
 * Created by luoyican on 2016/12/28.
 */
public class ContactAdapter extends BaseAdapter {
    private List<ContactMsgModel> data;
    private ContactMsgModel contactMsgModel;

    public ContactAdapter(List<ContactMsgModel> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ContactMsgModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, final ViewGroup group) {
        if (view == null) {
            view = new ContactItem(group.getContext(), null);
        }
        contactMsgModel = getItem(position);
        final ContactItem contactItem = (ContactItem) view;
        contactItem.setData(contactMsgModel);

        contactItem.setOnHeaderClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromCamera((Activity) group.getContext());
            }

        });
        return view;
    }

    private void getImageFromCamera(Activity activity) {
        try {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                String outputPath = Environment.getExternalStorageDirectory() + "/" + "MyApp/images/camera";
                File dir = new File(outputPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String capturePath = outputPath + "/" + System.currentTimeMillis() + ".jpg";
                getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(capturePath)));
                getImageByCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                activity.startActivityForResult(getImageByCamera, 0);
            } else {
                ToastUtil.show("确定插入SD卡");
            }
        } catch (Exception e) {

        }
    }

}
