package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.Customer;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.presenter.contract.CustomerContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/6/29.
 */

public class CustomerModel implements CustomerContract.ICustomerModel{


    public CustomerModel() {

    }

    @Override
    public Observable<BaseResponse<List<Customer>>> getCustomers(String userId) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getAllCustomersByUserID(userId);
    }

    @Override
    public Observable<BaseResponse<List<CustomerBean>>> getCustomerBeans(String userId) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getAllCustomerBeanByUserID(userId);
    }

    @Override
    public Observable<BaseResponse<List<Customer>>> getHeatStations(int userId) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getAllHeatStationsByUserID(userId);
    }
}

