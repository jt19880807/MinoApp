package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.AreaBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.presenter.contract.AreaContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/8/16.
 */

public class AreaModel implements AreaContract.IAreaModel {
//    @Override
//    public Observable<BaseResponse<PageBean<AreaBean>>> getAreas(int customerId, int pageIndex, int pageSize) {
//        ApiService apiService= RetrofitClient.getInstance().getApiService();
//        return apiService.getAreaDetailByCustomerId(customerId, pageIndex, pageSize);
//    }

    @Override
    public Observable<BaseResponse<List<AreaBean>>> getAreas(int customerId) {
        ApiService apiService= RetrofitClient.getInstance().getApiService();
        return apiService.getAreaDetailByCustomerId(customerId);
    }
}
