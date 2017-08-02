package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.ObjectBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.presenter.contract.ObjectContract;
import io.reactivex.Observable;

/**
 * Created by Devin on 2017/7/13.
 */

public class ObjectModel implements ObjectContract.IObjectModel {



    @Override
    public Observable<BaseResponse<PageBean<ObjectBean>>> getAllObjects(int userId, int customerId,int pageIndex,int pageSize) {

        ApiService apiservice= RetrofitClient.getInstance().getApiService();

        return apiservice.getAllObjects(userId,customerId,pageIndex,pageSize);
    }
}
