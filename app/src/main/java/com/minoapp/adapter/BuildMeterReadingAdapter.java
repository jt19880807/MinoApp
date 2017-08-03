package com.minoapp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.data.bean.BuildMeterReadingBean;

import java.util.List;

/**
 * Created by Devin on 2017/8/2.
 */

public class BuildMeterReadingAdapter extends BaseQuickAdapter<BuildMeterReadingBean,BaseViewHolder> {
    public BuildMeterReadingAdapter(@LayoutRes int layoutResId, @Nullable List<BuildMeterReadingBean> data) {
        super(layoutResId, data);
    }

    public BuildMeterReadingAdapter(@Nullable List<BuildMeterReadingBean> data) {
        super(data);
    }

    public BuildMeterReadingAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BuildMeterReadingBean item) {
        helper.setText(R.id.tv_br_reading_bh,item.getMeterNumber())
                .setText(R.id.tv_br_reading_date,item.getDate().replace('T',' '))
                .setText(R.id.tv_br_reading_ljrl,item.getValue()+"Mwh")
                .setText(R.id.tv_br_reading_ssrl,item.getInstantaneousHeat()+"KW")
                .setText(R.id.tv_br_reading_ljll,item.getAccumulationFlow()+"m³")
                .setText(R.id.tv_br_reading_ssll,item.getInstantaneousFlow()+"m³/h")
                .setText(R.id.tv_br_reading_gswd,item.getSupplyWaterTemperature()+"℃")
                .setText(R.id.tv_br_reading_hswd,item.getReturnWaterTemperature()+"℃")
                .setText(R.id.tv_br_reading_ljgzsj,item.getAccumulationWorkingTime()+"h");

    }
}
