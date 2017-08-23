package com.minoapp.data.bean;

/**
 * Created by Devin on 2017/8/2.
 */

public class BuildMeterReadingBean extends ReadingBean {

    private double InstantaneousFlow;
    private double AccumulationFlow;
    private double SupplyWaterTemperature;
    private double ReturnWaterTemperature;
    private double AccumulationWorkingTime;
    private double InstantaneousHeat;

    public double getInstantaneousFlow() {
        return InstantaneousFlow;
    }

    public void setInstantaneousFlow(double InstantaneousFlow) {
        this.InstantaneousFlow = InstantaneousFlow;
    }

    public double getAccumulationFlow() {
        return AccumulationFlow;
    }

    public void setAccumulationFlow(double AccumulationFlow) {
        this.AccumulationFlow = AccumulationFlow;
    }

    public double getSupplyWaterTemperature() {
        return SupplyWaterTemperature;
    }

    public void setSupplyWaterTemperature(double SupplyWaterTemperature) {
        this.SupplyWaterTemperature = SupplyWaterTemperature;
    }

    public double getReturnWaterTemperature() {
        return ReturnWaterTemperature;
    }

    public void setReturnWaterTemperature(double ReturnWaterTemperature) {
        this.ReturnWaterTemperature = ReturnWaterTemperature;
    }

    public double getAccumulationWorkingTime() {
        return AccumulationWorkingTime;
    }

    public void setAccumulationWorkingTime(double AccumulationWorkingTime) {
        this.AccumulationWorkingTime = AccumulationWorkingTime;
    }

    public double getInstantaneousHeat() {
        return InstantaneousHeat;
    }

    public void setInstantaneousHeat(double InstantaneousHeat) {
        this.InstantaneousHeat = InstantaneousHeat;
    }
}
