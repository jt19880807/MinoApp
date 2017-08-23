package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.HCABean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.data.bean.MeterBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/7/19.
 */

public interface MeterContract {
    public interface HeatMeterView extends BaseView {
        void showHeatMeter(List<HeatMeterBean> beanList);
        void showTemp(List<MeterBean> beanList);
        void showHCA(List<HCABean> beanList);
        void onLoadMoreComplete();
    }

}
