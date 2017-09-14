package com.minoapp.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.data.bean.AreaBean;

/**
 * Created by Devin on 2017/8/16.
 */

public class AreaAdapter extends BaseQuickAdapter<AreaBean,BaseViewHolder> {
    public AreaAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaBean item) {
//        helper.setText(R.id.tv_area_name,item.getName())
//                .setText(R.id.tv_build_count,"（"+item.getBuildingCount()+"）");
    }
}
