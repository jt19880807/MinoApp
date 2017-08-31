package com.minoapp.data.bean;

import java.util.List;

/**
 * Created by Devin on 2017/8/31.
 */

public class HeatStation {
    private String Address;
    private List<HeatStationBean> Data;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public List<HeatStationBean> getData() {
        return Data;
    }

    public void setData(List<HeatStationBean> data) {
        Data = data;
    }
}
