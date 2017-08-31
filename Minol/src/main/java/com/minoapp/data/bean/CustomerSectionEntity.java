package com.minoapp.data.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Administrator on 2017/8/13.
 */

public class CustomerSectionEntity<T> extends SectionEntity<T> {
    public String title;
    public T customerBean;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String pyCity;//城市的拼音

    public String getPyCity() {
        return pyCity;
    }

    public void setPyCity(String pyCity) {
        this.pyCity = pyCity;
    }
    public CustomerSectionEntity(boolean isHeader, String header,String city) {
        super(isHeader, header);
        this.title=header;
        this.city=city;
    }

    public CustomerSectionEntity(T customerBean,String city) {
        super(customerBean);
        this.customerBean=customerBean;
        this.city=city;
    }
}
