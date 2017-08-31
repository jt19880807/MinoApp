package com.minoapp.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.data.bean.CustomerSectionEntity;
import com.minoapp.data.bean.HeatStationBean;

import java.util.List;

/**
 * Created by Devin on 2017/8/31.
 */

public class HeatStationsAdapter extends BaseSectionQuickAdapter<CustomerSectionEntity<HeatStationBean>,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public HeatStationsAdapter(int layoutResId, int sectionHeadResId, List<CustomerSectionEntity<HeatStationBean>> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, CustomerSectionEntity<HeatStationBean> item) {
        helper.setText(R.id.tv_hca_reading_header,item.title);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerSectionEntity<HeatStationBean> item) {
        helper.setText(R.id.tv_customerarea,item.customerBean.getName());
    }
}
