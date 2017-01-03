package com.example.luoyican.myapp.ui.contact;

/**
 * Created by luoyican on 2016/12/28.
 */
public class ContactMsgModel {
    private String name;
    private String phone;
    private String from;//来源本地or sim卡

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
