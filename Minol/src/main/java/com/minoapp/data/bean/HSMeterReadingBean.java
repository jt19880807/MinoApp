package com.minoapp.data.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Devin on 2017/9/1.
 */

public class HSMeterReadingBean implements MultiItemEntity {
    private String MeterNumber;
    private int MeterType;
    private String Date;
    private double SHLL;
    private double SHRL;
    private double LJLL;
    private double LJRL;
    private double GSWD;
    private double HSWD;
    private double SWWD;
    private double PL;
    private double GSYL;
    private double HSYL;
    private double YDL;
    private double YSL;

    public int getMeterType() {
        return MeterType;
    }

    public void setMeterType(int meterType) {
        MeterType = meterType;
    }
    public String getMeterNumber() {
        return MeterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        MeterNumber = meterNumber;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getSHLL() {
        return SHLL;
    }

    public void setSHLL(double SHLL) {
        this.SHLL = SHLL;
    }

    public double getSHRL() {
        return SHRL;
    }

    public void setSHRL(double SHRL) {
        this.SHRL = SHRL;
    }

    public double getLJLL() {
        return LJLL;
    }

    public void setLJLL(double LJLL) {
        this.LJLL = LJLL;
    }

    public double getLJRL() {
        return LJRL;
    }

    public void setLJRL(double LJRL) {
        this.LJRL = LJRL;
    }

    public double getGSWD() {
        return GSWD;
    }

    public void setGSWD(double GSWD) {
        this.GSWD = GSWD;
    }

    public double getHSWD() {
        return HSWD;
    }

    public void setHSWD(double HSWD) {
        this.HSWD = HSWD;
    }

    public double getSWWD() {
        return SWWD;
    }

    public void setSWWD(double SWWD) {
        this.SWWD = SWWD;
    }

    public double getPL() {
        return PL;
    }

    public void setPL(double PL) {
        this.PL = PL;
    }

    public double getGSYL() {
        return GSYL;
    }

    public void setGSYL(double GSYL) {
        this.GSYL = GSYL;
    }

    public double getHSYL() {
        return HSYL;
    }

    public void setHSYL(double HSYL) {
        this.HSYL = HSYL;
    }

    public double getYDL() {
        return YDL;
    }

    public void setYDL(double YDL) {
        this.YDL = YDL;
    }

    public double getYSL() {
        return YSL;
    }

    public void setYSL(double YSL) {
        this.YSL = YSL;
    }

    @Override
    public int getItemType() {
        return MeterType;
    }
}
