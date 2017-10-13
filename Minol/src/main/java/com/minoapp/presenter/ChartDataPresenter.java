package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.ChartDataBean;
import com.minoapp.presenter.contract.ChartDataContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/9/18.
 */

public class ChartDataPresenter extends BasePresenter<ChartDataContract.IchartDataModel,ChartDataContract.ChartDataView> {
    public ChartDataPresenter(ChartDataContract.IchartDataModel ichartDataModel, ChartDataContract.ChartDataView chartDataView) {
        super(ichartDataModel, chartDataView);
    }

    public void getChartData(String meterIds,String startDate,String endDate){
        Observer observer= new ProgressSubcriber<List<ChartDataBean>>(context, view) {
            @Override
            public void onNext(@NonNull List<ChartDataBean> chartDataBeen) {
                view.showData(chartDataBeen);
            }
        };

        model.getChartData(meterIds, startDate, endDate)
                .compose(RxHttpReponseCompat.<List<ChartDataBean>>compatResult())
                .subscribe(observer);

    }
}
