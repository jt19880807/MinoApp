package com.minoapp.data.bean;

/**
 * Created by Devin on 2017/8/31.
 */

public class HeatStationBean extends CustomerBean {
    private String MeterCount;

    public HeatStationBean(String name) {
        super(name);
    }

    public String getMeterCount() {
        return MeterCount;
    }

    public void setMeterCount(String meterCount) {
        MeterCount = meterCount;
    }
}
