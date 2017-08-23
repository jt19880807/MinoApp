package com.minoapp.data.model;


import com.minoapp.api.ApiService;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.UserBean;
import com.minoapp.presenter.contract.LoginContract;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/7/4.
 */

public class LoginModel implements LoginContract.ILoginModel {

    private ApiService apiService;

    public LoginModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseResponse<UserBean>> getUserInfo(String name, String pwd) {
        return apiService.getUserInfo(name,pwd);
    }
}
