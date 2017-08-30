package com.minoapp.data.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.minoapp.base.BaseEntity;

/**
 * Created by Devin on 2017/7/19.
 */

public class MeterBean extends BaseEntity implements MultiItemEntity {
    public static final int HEATMETER = 1;
    public static final int HCA = 2;
    public static final int TEMP=3;
    private int ID;
    private String MeterNumber;
    private int LocalityId;
    private String LocalityNum;
    private int Type;
    private int RadiatorId;
    private boolean IsDeleted;
    private int LocalitieType;
    private int MeasuringPoint;
    private int MeterType;
    private String Position;

    public int getMeterType() {
        return MeterType;
    }

    public void setMeterType(int meterType) {
        MeterType = meterType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMeterNumber() {
        return MeterNumber;
    }

    public void setMeterNumber(String MeterNumber) {
        this.MeterNumber = MeterNumber;
    }

    public int getLocalityId() {
        return LocalityId;
    }

    public void setLocalityId(int LocalityId) {
        this.LocalityId = LocalityId;
    }

    public String getLocalityNum() {
        return LocalityNum;
    }

    public void setLocalityNum(String LocalityNum) {
        this.LocalityNum = LocalityNum;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public int getRadiatorId() {
        return RadiatorId;
    }

    public void setRadiatorId(int RadiatorId) {
        this.RadiatorId = RadiatorId;
    }

    public boolean isIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(boolean IsDeleted) {
        this.IsDeleted = IsDeleted;
    }

    public int getLocalitieType() {
        return LocalitieType;
    }

    public void setLocalitieType(int LocalitieType) {
        this.LocalitieType = LocalitieType;
    }

    public int getMeasuringPoint() {
        return MeasuringPoint;
    }

    public void setMeasuringPoint(int MeasuringPoint) {
        this.MeasuringPoint = MeasuringPoint;
    }

    @Override
    public int getItemType() {
        return MeterType;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }
}
