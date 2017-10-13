package com.minoapp.data.bean;

import java.util.List;

/**
 * Created by Devin on 2017/9/18.
 */

public class ChartDataBean extends MeterBean {

    private List<ChartReadingBean> data;


    public List<ChartReadingBean> getData() {
        return data;
    }

    public void setData(List<ChartReadingBean> data) {
        this.data = data;
    }
}
