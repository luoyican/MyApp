package com.example.luoyican.myapp.ui.contact;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luoyican.myapp.R;

/**
 * Created by luoyican on 2016/12/28.
 */
public class ContactItem extends FrameLayout {
    private LinearLayout viewItemContact;
    private ImageView ivHeader;
    private TextView tvName;
    private TextView tvPhone;
    private ImageView ivSendMsg;
    private ImageView ivCall;

    private OnClickListenerInterface mOnClickListenerInterface;

    public ContactItem(Context context) {
        super(context);
        bindViews();
    }

    public ContactItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        bindViews();
    }

    private void bindViews() {
        View view = View.inflate(getContext(), R.layout.item_contact, this);
        viewItemContact = (LinearLayout) view.findViewById(R.id.viewItemContact);
        ivHeader = (ImageView) view.findViewById(R.id.ivHeader);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvPhone = (TextView) view.findViewById(R.id.tvPhone);
        ivSendMsg = (ImageView) view.findViewById(R.id.ivSendMsg);
        ivCall = (ImageView) view.findViewById(R.id.ivCall);
    }

    public void setData(ContactMsgModel contactMsgModel) {
        tvName.setText(contactMsgModel.getName());
        tvPhone.setText(contactMsgModel.getPhone());
    }

    public void setHeader(Uri uri) {
        ivHeader.setImageURI(uri);
    }

    public void setOnHeaderClick(OnClickListener onHeaderClick) {
        ivHeader.setOnClickListener(onHeaderClick);
    }

    public void setOnMsgClick(OnClickListener onMsgClick) {
        ivSendMsg.setOnClickListener(onMsgClick);
    }

    public void setOnCallClick(OnClickListener onCallClick) {
        ivCall.setOnClickListener(onCallClick);
    }


}
