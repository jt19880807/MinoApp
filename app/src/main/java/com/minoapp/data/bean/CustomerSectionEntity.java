package com.minoapp.data.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Administrator on 2017/8/13.
 */

public class CustomerSectionEntity extends SectionEntity<CustomerBean> {
    public String title;
    public CustomerBean customerBean;
    public CustomerSectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
        this.title=header;
    }

    public CustomerSectionEntity(CustomerBean customerBean) {
        super(customerBean);
        this.customerBean=customerBean;
    }
}
