package com.minoapp.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.data.bean.BillingInfoBean;
import com.minoapp.data.bean.ResidentBean;

import java.util.List;

/**
 * Created by Devin on 2017/8/9.
 */

public class BillingInfoAdapter extends BaseMultiItemQuickAdapter<BillingInfoBean,BaseViewHolder> {

    public BillingInfoAdapter(List<BillingInfoBean> data) {
        super(data);
        addItemType(BillingInfoBean.PAYONAREA, R.layout.billing_area_item);
        addItemType(BillingInfoBean.PAYONMETER,R.layout.billing_meter_item);
    }


    @Override
    protected void convert(BaseViewHolder helper, BillingInfoBean item) {
        switch (item.getItemType()){
            case BillingInfoBean.PAYONAREA:
                helper.setText(R.id.tv_anumber,item.getNumber())
                        .setText(R.id.tv_ausername,item.getName())
                        .setText(R.id.tv_aarea,item.getArea()+"")
                        .setText(R.id.tv_ausearea,item.getAreaUseHeat()+"")
                        .setText(R.id.tv_asedate,item.getStartDate()+"至"+item.getEndDate())
                        .setText(R.id.tv_aysrf,item.getAreaCost()+"");
                break;
            case BillingInfoBean.PAYONMETER:
                helper.setText(R.id.tv_mnumber,item.getNumber())
                        .setText(R.id.tv_musername,item.getName())
                        .setText(R.id.tv_marea,item.getArea()+"")
                        .setText(R.id.tv_musearea,item.getAreaUseHeat()+"")
                        .setText(R.id.tv_msedate,item.getStartDate()+"至"+item.getEndDate())
                        .setText(R.id.tv_mjcrf,item.getBaseCost()+"")
                        .setText(R.id.tv_mjlrf,item.getHeatCost()+"")
                        .setText(R.id.tv_mysrf,item.getTotalCost()+"")
                        .setText(R.id.tv_mmjfy,item.getAreaCost()+"")
                        .setText(R.id.tv_mtfje,item.getRefundRate()+"");
                break;
        }
    }
}

