package com.minoapp.data.bean;

import com.minoapp.base.BaseEntity;

/**
 * Created by Devin on 2017/6/27.
 */

public class CustomerBean extends BaseEntity {
    private int ID;
    private String Name;
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

    public int getID() {
        return ID;
    }

    public void setID(int Id) {
        this.ID = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

}
