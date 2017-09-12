package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.HSMeterReadingBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.presenter.contract.HSReadingsContract;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/9/8.
 */

public class HSReadingModel implements HSReadingsContract.IHSReadingModel {
    @Override
    public Observable<BaseResponse<PageBean<HSMeterReadingBean>>> getHSReadings(int meterId, String startDate, String endDate, int pageIndex, int pageSize, int meterType) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getHeatStationMeterReadings(meterId, startDate, endDate, pageIndex, pageSize, meterType);
    }

    @Override
    public Observable<BaseResponse<PageBean<HSMeterReadingBean>>> getHSLastReadings(int meterId,int meterType) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getHeatStationMeterLastReadings(meterId, meterType);
    }
}
