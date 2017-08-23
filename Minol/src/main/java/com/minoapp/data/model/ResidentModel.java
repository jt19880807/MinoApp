package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.bean.ResidentBean;
import com.minoapp.presenter.contract.ResidentsContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/7/17.
 */

public class ResidentModel implements ResidentsContract.IResidentsModel {


    @Override
    public Observable<BaseResponse<PageBean<ResidentBean>>> getResidents(int objectID, int pageIndex, int pageSize) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getResidents(objectID,pageIndex,pageSize);
    }

    @Override
    public Observable<BaseResponse<List<ResidentBean>>> getResidents(int localityId) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getResidents(localityId);

    }
}
