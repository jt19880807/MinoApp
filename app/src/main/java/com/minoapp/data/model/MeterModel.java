package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.HCABean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.data.bean.MeterBean;
import com.minoapp.presenter.contract.IMeterModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/7/19.
 */

public class MeterModel implements IMeterModel {
    @Override
    public Observable<BaseResponse<List<HeatMeterBean>>> getBuildMetersByObjectId(int objectID) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getHeatMetersByObjectId(objectID);
    }

    @Override
    public Observable<BaseResponse<List<MeterBean>>> getTempByObjectId(int objectID) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getTempByObjectId(objectID);
    }

    @Override
    public Observable<BaseResponse<List<HCABean>>> getHCAByLocalityId(int localityId) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getHCAByLocalityId(localityId);
    }


}
