package com.minoapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.data.bean.ObjectBean;

import java.util.List;

/**
 * Created by Devin on 2017/7/12.
 */

public class ObjectsAdapter extends BaseQuickAdapter<ObjectBean,BaseViewHolder> {


    public ObjectsAdapter(int layoutResId, List<ObjectBean> data) {
        super(layoutResId, data);
    }

    public ObjectsAdapter(List<ObjectBean> data) {
        super(data);
    }

    public ObjectsAdapter(int layoutResId) {
        super(layoutResId);
    }
    public ObjectsAdapter() {
        super(R.layout.object_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, ObjectBean item) {
        helper.setText(R.id.tv_objectname,item.getName())
                .setText(R.id.tv_totaluser,item.getResidentsCount()+"")
                .setText(R.id.tv_gzuser,item.getTransResidentsCount()+"")
                .setText(R.id.tv_heatmeter,item.getHeatMeterCount()+"")
                .setText(R.id.tv_hca,item.getHCACount()+"")
                .setText(R.id.tv_temp,item.getTempMeterCount()+"")
                .setText(R.id.tv_incidents,item.getIncedentsCount());
    }

    @Override
    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener) {
        super.setOnLoadMoreListener(requestLoadMoreListener);
    }
}
