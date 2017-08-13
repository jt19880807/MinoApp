package com.minoapp.data.bean;

import java.util.List;

/**
 * Created by Devin on 2017/7/28.
 */

public class HCAReading {

    private String Date;
    private List<ReadingBean> Data;

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public List<ReadingBean> getData() {
        return Data;
    }

    public void setData(List<ReadingBean> Data) {
        this.Data = Data;
    }


}
