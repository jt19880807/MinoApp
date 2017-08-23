package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.IncidentBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.presenter.contract.IncidentContract;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/8/11.
 */

public class IncidentModel implements IncidentContract.IincidentModel {
    @Override
    public Observable<BaseResponse<PageBean<IncidentBean>>> getIncidents(int objectId, int pageIndex, int pageSize) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getIncidentsByObjectId(objectId,pageIndex,pageSize);
    }
}
