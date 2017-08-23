package com.minoapp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.data.bean.ReadingBean;

import java.util.List;

/**
 * Created by Devin on 2017/8/2.
 */

public class TempReadingAdapter extends BaseQuickAdapter<ReadingBean,BaseViewHolder> {
    public TempReadingAdapter(@LayoutRes int layoutResId, @Nullable List<ReadingBean> data) {
        super(layoutResId, data);
    }

    public TempReadingAdapter(@Nullable List<ReadingBean> data) {
        super(data);
    }

    public TempReadingAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReadingBean item) {
        helper.setText(R.id.tv_temp_reading_bh,item.getMeterNumber())
                .setText(R.id.tv_temp_reading_date,item.getDate())
                .setText(R.id.tv_temp_reading_snwd,item.getValue()+"℃")
                .setText(R.id.tv_temp_reading_pjwd,item.getValue()+"℃");

    }
}
