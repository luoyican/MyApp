package com.example.luoyican.myapp.ui.contact;

/**
 * Created by luoyican on 2016/12/29.
 */
public interface OnClickListenerInterface {
    /**
     * click event for conversation list
     *
     * @param contactMsgModel -- clicked item
     */
    void onListItemClicked(ContactMsgModel contactMsgModel);

    //头像点击监听
    void onListItemAvatorClicked(ContactMsgModel contactMsgModel);
}
