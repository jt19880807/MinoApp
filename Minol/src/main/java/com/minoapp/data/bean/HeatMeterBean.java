package com.minoapp.data.bean;

/**
 * Created by Devin on 2017/7/19.
 */

public class HeatMeterBean extends MeterBean {
    
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
