package com.minoapp.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.common.utils.MeterUtil;
import com.minoapp.data.bean.HCABean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.data.bean.MeterBean;
import com.minoapp.data.bean.ResidentBean;

import java.util.List;

/**
 * Created by Devin on 2017/7/17.
 */



public class MeterListAdapter<T extends MeterBean> extends BaseMultiItemQuickAdapter<T,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MeterListAdapter(List<T> data) {
        super(data);
        addItemType(MeterBean.HEATMETER, R.layout.heatmeter_item);
        addItemType(MeterBean.TEMP, R.layout.temp_item);
        addItemType(MeterBean.HCA,R.layout.hcalist_item);
    }



    @Override
    protected void convert(BaseViewHolder helper, T item) {

        switch (item.getItemType()){
            case MeterBean.HEATMETER:
                HeatMeterBean heatMeterBean=(HeatMeterBean)item;
                helper.setText(R.id.tv_heatmeter_number,heatMeterBean.getMeterNumber())
                        .setText(R.id.tv_heatmeter_product,heatMeterBean.getManufacturerName())
                        .setText(R.id.tv_heatmeter_type,heatMeterBean.getProductNum());
                break;
            case MeterBean.HCA:
                HCABean hcaBean=(HCABean)item;
                helper.setText(R.id.tv_hca_number,hcaBean.getMeterNumber())
                        .setText(R.id.tv_hca_txbm,hcaBean.getIdentificationNumber())
                        .setText(R.id.tv_hca_position, MeterUtil.getHCAPositon(hcaBean.getPosition()));
                break;
            case MeterBean.TEMP:
                helper.setText(R.id.tv_temp_number,item.getMeterNumber())
                        .setText(R.id.tv_temp_roomnum,item.getLocalityNum());
                break;

        }
    }
}

