package com.minoapp.data.bean;

import com.minoapp.base.BaseEntity;

/**
 * Created by Devin on 2017/6/19.
 */

public class MenuBean extends BaseEntity {

    String text;
    int iconID;

    public MenuBean(String text, int iconID) {
        this.text = text;
        this.iconID = iconID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }
    //    int
}
