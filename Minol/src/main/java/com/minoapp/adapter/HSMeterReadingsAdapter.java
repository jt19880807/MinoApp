package com.minoapp.adapter;

import android.provider.Settings;
import android.util.Log;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.common.Constant;
import com.minoapp.data.bean.HSMeterReadingBean;

import java.util.List;

/**
 * Created by Devin on 2017/9/8.
 */

public class HSMeterReadingsAdapter extends BaseMultiItemQuickAdapter<HSMeterReadingBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HSMeterReadingsAdapter(List<HSMeterReadingBean> data) {
        super(data);
        addItemType(Constant.HEATSTATION_METER_RLB, R.layout.hs_rlb_reading);
        addItemType(Constant.HEATSTATION_METER_QHBCQ,R.layout.hs_qhbcq_reading);
        addItemType(Constant.HEATSTATION_METER_BPQ,R.layout.hs_bpq_reading);
        addItemType(Constant.HEATSTATION_METER_SB,R.layout.hs_sb_reading);
        addItemType(Constant.HEATSTATION_METER_DB,R.layout.hs_db_reading);
    }

    @Override
    protected void convert(BaseViewHolder helper, HSMeterReadingBean item) {
        //Log.d("itemtype",item.getItemType()+"");
        switch (item.getItemType()){
            case Constant.HEATSTATION_METER_RLB:
                helper.setText(R.id.tv_hs_reading_bh,item.getMeterNumber())
                        .setText(R.id.tv_hs_reading_date,item.getDate().replace('T',' '))
                        .setText(R.id.tv_hs_reading_ssll,item.getSHLL()+"")
                        .setText(R.id.tv_hs_reading_ssrl,item.getSHRL()+"")
                        .setText(R.id.tv_hs_reading_ljll,item.getLJLL()+"")
                        .setText(R.id.tv_hs_reading_ljrl,item.getLJRL()+"")
                        .setText(R.id.tv_hs_reading_gswd,item.getGSWD()+"")
                        .setText(R.id.tv_hs_reading_hswd,item.getHSWD()+"");
                break;
            case Constant.HEATSTATION_METER_QHBCQ:
                helper.setText(R.id.tv_hs_reading_bh,item.getMeterNumber())
                        .setText(R.id.tv_hs_reading_date,item.getDate().replace('T',' '))
                        .setText(R.id.tv_hs_reading_gswd_q,item.getGSWD()+"")
                        .setText(R.id.tv_hs_reading_hswd_q,item.getHSWD()+"")
                        .setText(R.id.tv_hs_reading_swwd,item.getSWWD()+"");
                break;
            case Constant.HEATSTATION_METER_BPQ:
                helper.setText(R.id.tv_hs_reading_bh,item.getMeterNumber())
                        .setText(R.id.tv_hs_reading_date,item.getDate().replace('T',' '))
                        .setText(R.id.tv_hs_reading_pl,item.getPL()+"")
                        .setText(R.id.tv_hs_reading_gsyl,item.getGSYL()+"")
                        .setText(R.id.tv_hs_reading_hsyl,item.getHSYL()+"");
                break;
            case Constant.HEATSTATION_METER_DB:
                helper.setText(R.id.tv_hs_reading_bh,item.getMeterNumber())
                        .setText(R.id.tv_hs_reading_date,item.getDate().replace('T',' '))
                        .setText(R.id.tv_hs_reading_ydl,item.getYDL()+"");
                break;
            case Constant.HEATSTATION_METER_SB:
                helper.setText(R.id.tv_hs_reading_bh,item.getMeterNumber())
                        .setText(R.id.tv_hs_reading_date,item.getDate().replace('T',' '))
                        .setText(R.id.tv_hs_reading_ysl,item.getYSL()+"");
                break;
        }
    }
}
