package com.minoapp.data.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.minoapp.base.BaseBean;

/**
 * Created by Devin on 2017/8/16.
 */

public class AreaBean extends BaseBean implements MultiItemEntity {

    public AreaBean(String name,int buildingCount) {
        super(name);
        BuildingCount = buildingCount;
    }

    private int BuildingCount;

    public int getBuildingCount() {
        return BuildingCount;
    }

    public void setBuildingCount(int BuildingCount) {
        this.BuildingCount = BuildingCount;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}



