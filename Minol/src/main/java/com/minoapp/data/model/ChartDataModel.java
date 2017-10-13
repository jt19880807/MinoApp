package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.ChartDataBean;
import com.minoapp.presenter.contract.ChartDataContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/9/18.
 */

public class ChartDataModel implements ChartDataContract.IchartDataModel {
    @Override
    public Observable<BaseResponse<List<ChartDataBean>>> getChartData(String meterids, String startDate, String endData) {
        ApiService apiService= RetrofitClient.getInstance().getApiService();

        return apiService.getChartData(meterids, startDate, endData);
    }
}
