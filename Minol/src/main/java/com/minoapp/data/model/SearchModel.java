package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.AreaBean;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.HeatStationBean;
import com.minoapp.presenter.contract.SearchContact;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/9/12.
 */

public class SearchModel implements SearchContact.SearchModel {
    @Override
    public Observable<BaseResponse<List<CustomerBean>>> getCustomerBeans(int userId) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getAllCustomerBeanByUserID(userId);
    }

    @Override
    public Observable<BaseResponse<List<AreaBean>>> getAreaBeans(int customerId) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getAreasByCustomerId(customerId);
    }

    @Override
    public Observable<BaseResponse<List<HeatStationBean>>> getHeatStationBeans(int userId) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getAllHeatStationBeans(userId);
    }
}
