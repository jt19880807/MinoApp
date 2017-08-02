package com.minoapp.data.bean;

import java.io.Serializable;

/**
 * Created by Devin on 2017/7/12.
 */

public class ObjectBean implements Serializable {


    /**
     * ID : 1340
     * Name : 靛厂路6号院10号楼
     * ResidentsCount : 39
     * TransResidentsCount : 34
     * HeatMeterCount : 1
     * HCACount : 216
     * TempMeterCount : 9
     */

    private int ID;
    private String Name;
    private int ResidentsCount;
    private int TransResidentsCount;
    private int HeatMeterCount;
    private int HCACount;
    private int TempMeterCount;

    public ObjectBean(int ID, String name, int residentsCount, int transResidentsCount, int heatMeterCount, int HCACount, int tempMeterCount) {
        this.ID = ID;
        Name = name;
        ResidentsCount = residentsCount;
        TransResidentsCount = transResidentsCount;
        HeatMeterCount = heatMeterCount;
        this.HCACount = HCACount;
        TempMeterCount = tempMeterCount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getResidentsCount() {
        return ResidentsCount;
    }

    public void setResidentsCount(int ResidentsCount) {
        this.ResidentsCount = ResidentsCount;
    }

    public int getTransResidentsCount() {
        return TransResidentsCount;
    }

    public void setTransResidentsCount(int TransResidentsCount) {
        this.TransResidentsCount = TransResidentsCount;
    }

    public int getHeatMeterCount() {
        return HeatMeterCount;
    }

    public void setHeatMeterCount(int HeatMeterCount) {
        this.HeatMeterCount = HeatMeterCount;
    }

    public int getHCACount() {
        return HCACount;
    }

    public void setHCACount(int HCACount) {
        this.HCACount = HCACount;
    }

    public int getTempMeterCount() {
        return TempMeterCount;
    }

    public void setTempMeterCount(int TempMeterCount) {
        this.TempMeterCount = TempMeterCount;
    }
}
