package com.minoapp.data.bean;

/**
 * Created by Devin on 2017/7/19.
 */

public class HeatMeterBean extends MeterBean {

    /**
     * ArticleId : 280
     * ProductNum : DN100
     * ManufacturerName : Engelmann
     * ID : 132475
     * MeterNumber : 48476106
     * LocalityId : 0
     * LocalityNum : null
     * Type : 0
     * RadiatorId : null
     * IsDeleted : false
     * LocalitieType : 0
     * MeasuringPoint : 0
     */

    private int ArticleId;
    private String ProductNum;
    private String ManufacturerName;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    private String Description;
    public int getArticleId() {
        return ArticleId;
    }

    public void setArticleId(int ArticleId) {
        this.ArticleId = ArticleId;
    }

    public String getProductNum() {
        return ProductNum;
    }

    public void setProductNum(String ProductNum) {
        this.ProductNum = ProductNum;
    }

    public String getManufacturerName() {
        return ManufacturerName;
    }

    public void setManufacturerName(String ManufacturerName) {
        this.ManufacturerName = ManufacturerName;
    }
}
