package com.minoapp.data.bean;



import com.minoapp.base.BaseEntity;

import java.util.List;

/**
 * Created by Devin on 2017/6/14.
 */

public class UserBean extends BaseEntity {
    private int ID;
    private String LoginName;
    private String Password;
    private String Objects;
    private int IsDeleted;
    private String RoleName;
    private int LocalityId;
    private List<RightBean> ParentRights;
    private List<RightBean> ChildRights;

    public int getLocalityId() {
        return LocalityId;
    }

    public void setLocalityId(int localityId) {
        LocalityId = localityId;
    }
    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getObjects() {
        return Objects;
    }

    public void setObjects(String Objects) {
        this.Objects = Objects;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int IsDeleted) {
        this.IsDeleted = IsDeleted;
    }

    public List<RightBean> getParentRights() {
        return ParentRights;
    }

    public void setParentRights(List<RightBean> ParentRights) {
        this.ParentRights = ParentRights;
    }

    public List<RightBean> getChildRights() {
        return ChildRights;
    }

    public void setChildRights(List<RightBean> ChildRights) {
        this.ChildRights = ChildRights;
    }


    @Override
    public String toString() {
        return "Name:"+getLoginName()+"|Pwd:"+getPassword()+"|PMenu:"+getParentRights().size();
    }
}
