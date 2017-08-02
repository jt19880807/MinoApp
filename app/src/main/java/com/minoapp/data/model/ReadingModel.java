package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.BuildMeterReadingBean;
import com.minoapp.data.bean.HCAReading;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.bean.ReadingBean;
import com.minoapp.presenter.contract.IReadingModel;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/7/28.
 */

public class ReadingModel implements IReadingModel {
    @Override
    public Observable<BaseResponse<PageBean<HCAReading>>> getHCAReadings(int localityId, String startDate, String endDate, int pageIndex, int pageSize) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getHCAReadings(localityId,startDate,endDate,pageIndex,pageSize);
    }

    @Override
    public Observable<BaseResponse<PageBean<BuildMeterReadingBean>>> getBuildMeterReadings(int meterId, String startDate, String endDate, int pageIndex, int pageSize) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getBuildMeterReadings(meterId,startDate,endDate,pageIndex,pageSize);
    }

    @Override
    public Observable<BaseResponse<PageBean<ReadingBean>>> getTempReadings(int meterId, String startDate, String endDate, int pageIndex, int pageSize) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getTempReadings(meterId,startDate,endDate,pageIndex,pageSize);
    }
}
