package com.minoapp.data.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.minoapp.base.BaseEntity;

/**
 * Created by Devin on 2017/7/17.
 */

public class ResidentBean extends BaseEntity implements MultiItemEntity {
    public static final int YIGAIZAO = 1;
    public static final int WEIGAIZAO = 0;
    /**
     * Id : 26224
     * Number : 1-101
     * Name : 罗薇
     * TelephoneNo : 13501001016
     * HCACount : 8
     * TempCount : 0
     * AreaUseHeat : 133.52
     * Area : 133.52
     */

    private int Id;
    private String Number;
    private String Name;
    private String TelephoneNo;
    private int HCACount;
    private int TempCount;
    private double AreaUseHeat;
    private double Area;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTelephoneNo() {
        return TelephoneNo;
    }

    public void setTelephoneNo(String TelephoneNo) {
        this.TelephoneNo = TelephoneNo;
    }

    public int getHCACount() {
        return HCACount;
    }

    public void setHCACount(int HCACount) {
        this.HCACount = HCACount;
    }

    public int getTempCount() {
        return TempCount;
    }

    public void setTempCount(int TempCount) {
        this.TempCount = TempCount;
    }

    public double getAreaUseHeat() {
        return AreaUseHeat;
    }

    public void setAreaUseHeat(double AreaUseHeat) {
        this.AreaUseHeat = AreaUseHeat;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double Area) {
        this.Area = Area;
    }

    @Override
    public int getItemType() {
        return HCACount>0?YIGAIZAO:WEIGAIZAO;
    }
}
