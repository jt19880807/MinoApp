package com.minoapp.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.common.utils.MeterUtil;
import com.minoapp.data.bean.HCAReadingSectionEntity;
import com.minoapp.ui.activity.ResidentDetailActivity;

import java.util.List;

/**
 * Created by Devin on 2017/7/28.
 */

public class HCAReadingAdapter extends BaseSectionQuickAdapter<HCAReadingSectionEntity,BaseViewHolder> {

    public HCAReadingAdapter(int layoutResId, int sectionHeadResId, List<HCAReadingSectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, HCAReadingSectionEntity item) {
        helper.setText(R.id.tv_hca_reading_header,item.title);
    }

    @Override
    protected void convert(BaseViewHolder helper, HCAReadingSectionEntity item) {
        helper.setText(R.id.tv_hca_reading_txbm,item.readingBean.getIdentificationNumber())
                .setText(R.id.tv_hca_reading_value,item.readingBean.getValue()+"")
        .setText(R.id.tv_hca_reading_position, MeterUtil.getHCAPositon(item.readingBean.getPosition()));
    }


}
