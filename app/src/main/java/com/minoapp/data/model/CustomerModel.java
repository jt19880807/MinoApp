package com.minoapp.data.model;

import com.minoapp.api.ApiService;
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

    private ApiService apiService;

    public CustomerModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseResponse<List<Customer>>> getCustomers(String userId) {

        //暂时写死
        return apiService.getAllCustomersByUserID(userId);
    }
}

