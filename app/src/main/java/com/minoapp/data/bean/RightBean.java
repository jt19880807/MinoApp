package com.minoapp.data.bean;

import com.minoapp.base.BaseEntity;

/**
 * Created by Devin on 2017/6/23.
 */

public class RightBean extends BaseEntity {
    /**
     * ID : 4
     * ParentID : 1
     * RightName : 项目管理
     */

    private int ID;
    private int ParentID;
    private String RightName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int ParentID) {
        this.ParentID = ParentID;
    }

    public String getRightName() {
        return RightName;
    }

    public void setRightName(String RightName) {
        this.RightName = RightName;
    }
}
