package com.minoapp.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.data.bean.ResidentBean;

import java.util.List;

/**
 * Created by Devin on 2017/7/17.
 */

public class ResidentsAdapter extends BaseMultiItemQuickAdapter<ResidentBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ResidentsAdapter(List<ResidentBean> data) {
        super(data);
        addItemType(ResidentBean.YIGAIZAO, R.layout.y_resident_item);
        addItemType(ResidentBean.WEIGAIZAO, R.layout.w_resident_item);
    }



    @Override
    protected void convert(BaseViewHolder helper, ResidentBean item) {
        switch (item.getItemType()){
            case ResidentBean.YIGAIZAO:
                helper.setText(R.id.tv_ynumber,item.getNumber())
                        .setText(R.id.tv_yusername,item.getName())
                .setText(R.id.tv_yarea,item.getArea()+"")
                .setText(R.id.tv_ytel,item.getTelephoneNo())
                .setText(R.id.tv_yusearea,item.getAreaUseHeat()+"")
                .setText(R.id.tv_yhcacount,item.getHCACount()+"")
                .setText(R.id.tv_ytempcount,item.getTempCount()+"");
                break;
            case ResidentBean.WEIGAIZAO:
                helper.setText(R.id.tv_ynumber,item.getNumber())
                        .setText(R.id.tv_yusername,item.getName())
                        .setText(R.id.tv_yarea,item.getArea()+"")
                        .setText(R.id.tv_ytel,item.getTelephoneNo())
                        .setText(R.id.tv_yusearea,item.getAreaUseHeat()+"");
                break;
        }
    }
}

