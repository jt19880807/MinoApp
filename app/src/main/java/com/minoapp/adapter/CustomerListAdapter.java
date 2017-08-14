package com.minoapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.CustomerSectionEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Devin on 2017/6/28.
 */

public class CustomerListAdapter extends BaseSectionQuickAdapter<CustomerSectionEntity,BaseViewHolder>{

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public CustomerListAdapter(int layoutResId, int sectionHeadResId, List<CustomerSectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, CustomerSectionEntity item) {
        helper.setText(R.id.tv_hca_reading_header,item.title);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerSectionEntity item) {
        helper.setText(R.id.tv_customerarea,item.customerBean.getName());
    }
}
