package com.minoapp.data.bean;

/**
 * Created by Devin on 2017/9/18.
 */

public class ChartReadingBean {
    /**
     * Date : 2017-03-15T00:39:23
     * Value : 24.0
     * MeterId : 225182
     */

    private String Date;
    private double Value;
    private int MeterId;

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public int getMeterId() {
        return MeterId;
    }

    public void setMeterId(int MeterId) {
        this.MeterId = MeterId;
    }
}
