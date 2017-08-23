package com.minoapp.data.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.minoapp.base.BaseEntity;

/**
 * Created by Devin on 2017/8/8.
 */

public class BillingInfoBean extends BaseEntity implements MultiItemEntity {
    public static final int PAYONMETER = 1;
    public static final int PAYONAREA = 0;

    private String Name;
    private Object ObjectName;
    private double ShareBase;
    private double Qz;
    private double Qm;
    private double Qj;
    private double TotalConsumption;
    private double BaseCost;
    private double HeatCost;
    private double TotalCost;
    private double RefundRate;
    private double AreaCost;
    private double BasePrice;
    private double HeatPrice;
    private double AreaPrice;
    private boolean IsAreaPay;
    private int Id;
    private String Number;
    private int ObjectID;
    private Object DeletedDate;
    private int Type;
    private double AreaUseHeat;
    private double Area;
    private String StartDate ;
    private String EndDate ;

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Object getObjectName() {
        return ObjectName;
    }

    public void setObjectName(Object ObjectName) {
        this.ObjectName = ObjectName;
    }

    public double getShareBase() {
        return ShareBase;
    }

    public void setShareBase(double ShareBase) {
        this.ShareBase = ShareBase;
    }

    public double getQz() {
        return Qz;
    }

    public void setQz(double Qz) {
        this.Qz = Qz;
    }

    public double getQm() {
        return Qm;
    }

    public void setQm(double Qm) {
        this.Qm = Qm;
    }

    public double getQj() {
        return Qj;
    }

    public void setQj(double Qj) {
        this.Qj = Qj;
    }

    public double getTotalConsumption() {
        return TotalConsumption;
    }

    public void setTotalConsumption(double TotalConsumption) {
        this.TotalConsumption = TotalConsumption;
    }

    public double getBaseCost() {
        return BaseCost;
    }

    public void setBaseCost(double BaseCost) {
        this.BaseCost = BaseCost;
    }

    public double getHeatCost() {
        return HeatCost;
    }

    public void setHeatCost(double HeatCost) {
        this.HeatCost = HeatCost;
    }

    public double getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(double TotalCost) {
        this.TotalCost = TotalCost;
    }

    public double getRefundRate() {
        return RefundRate;
    }

    public void setRefundRate(double RefundRate) {
        this.RefundRate = RefundRate;
    }

    public double getAreaCost() {
        return AreaCost;
    }

    public void setAreaCost(double AreaCost) {
        this.AreaCost = AreaCost;
    }

    public double getBasePrice() {
        return BasePrice;
    }

    public void setBasePrice(double BasePrice) {
        this.BasePrice = BasePrice;
    }

    public double getHeatPrice() {
        return HeatPrice;
    }

    public void setHeatPrice(double HeatPrice) {
        this.HeatPrice = HeatPrice;
    }

    public double getAreaPrice() {
        return AreaPrice;
    }

    public void setAreaPrice(double AreaPrice) {
        this.AreaPrice = AreaPrice;
    }

    public boolean isIsAreaPay() {
        return IsAreaPay;
    }

    public void setIsAreaPay(boolean IsAreaPay) {
        this.IsAreaPay = IsAreaPay;
    }

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

    public int getObjectID() {
        return ObjectID;
    }

    public void setObjectID(int ObjectID) {
        this.ObjectID = ObjectID;
    }

    public Object getDeletedDate() {
        return DeletedDate;
    }

    public void setDeletedDate(Object DeletedDate) {
        this.DeletedDate = DeletedDate;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
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
        return isIsAreaPay()?PAYONAREA:PAYONMETER;
    }
}
