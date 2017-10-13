package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.ChartDataBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/9/18.
 */

public interface ChartDataContract {
    interface  ChartDataView extends BaseView {
        void showData(List<ChartDataBean> chartDataBeen);
    }
    interface IchartDataModel{
        Observable<BaseResponse<List<ChartDataBean>>> getChartData(String meterids, String startDate, String endData);
    }
}
