package com.minoapp.data.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/13.
 */

public class Customer  {
    private String Address;
    private List<CustomerBean> Data;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public List<CustomerBean> getData() {
        return Data;
    }

    public void setData(List<CustomerBean> data) {
        Data = data;
    }
}
