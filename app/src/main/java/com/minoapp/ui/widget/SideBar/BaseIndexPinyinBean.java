package com.minoapp.ui.widget.SideBar;

/**
 * Created by Administrator on 2017/8/13.
 */

public abstract  class BaseIndexPinyinBean extends BaseIndexTagBean implements IIndexTargetInterface {
    private String pyCity;//城市的拼音

    public String getPyCity() {
        return pyCity;
    }

    public void setPyCity(String pyCity) {
        this.pyCity = pyCity;
    }
}
