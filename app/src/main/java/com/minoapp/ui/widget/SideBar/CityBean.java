package com.minoapp.ui.widget.SideBar;

/**
 * Created by Administrator on 2017/8/13.
 */

public class CityBean extends BaseIndexPinyinBean {
    private String city;//城市名字

    public CityBean() {
    }
    public CityBean(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String getTarget() {
        return city;
    }
}
