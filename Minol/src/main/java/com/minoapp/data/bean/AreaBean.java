package com.minoapp.data.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.minoapp.base.BaseBean;

/**
 * Created by Devin on 2017/8/16.
 */

public class AreaBean extends BaseBean {

    private String AreaName;
    private int BuildingCount;
    private int ID;
    private int ResidentsCount;
    private int TransResidentsCount;
    private int HeatMeterCount;
    private int HCACount;
    private int TempMeterCount;
    private int IncedentsCount;

    public AreaBean(String name, int buildingCount) {
        super(name);
        //BuildingCount = buildingCount;
    }


    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String AreaName) {
        this.AreaName = AreaName;
    }

    public int getBuildingCount() {
        return BuildingCount;
    }

    public void setBuildingCount(int BuildingCount) {
        this.BuildingCount = BuildingCount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getIncedentsCount() {
        return IncedentsCount;
    }

    public void setIncedentsCount(int IncedentsCount) {
        this.IncedentsCount = IncedentsCount;
    }
}



