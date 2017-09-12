package com.minoapp.data.bean;

import com.minoapp.base.BaseBean;

/**
 * Created by Devin on 2017/6/27.
 */

public class CustomerBean extends BaseBean {
    private int ID;
    private String Province;
    private String City;
    private String Area;

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public CustomerBean(int id, String name, int addressId) {
        ID = id;
        Name = name;
    }

    public CustomerBean() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int Id) {
        this.ID = Id;
    }



}
