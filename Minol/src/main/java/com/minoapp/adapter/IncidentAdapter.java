package com.minoapp.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.data.bean.IncidentBean;

/**
 * Created by Devin on 2017/8/11.
 */

public class IncidentAdapter extends BaseQuickAdapter<IncidentBean,BaseViewHolder> {
    public IncidentAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, IncidentBean item) {
        helper.setText(R.id.tv_incidentime,item.getCreatedDate().replace('T',' '))
                .setText(R.id.tv_incidentinfo,item.getDescription());
    }
}
