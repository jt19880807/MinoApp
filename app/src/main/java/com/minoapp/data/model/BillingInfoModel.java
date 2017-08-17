package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.BillingInfoBean;
import com.minoapp.data.bean.HeatSeasonBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.presenter.contract.BillingContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/8/8.
 */

public class BillingInfoModel implements BillingContract.IBillingModel {
    @Override
    public Observable<BaseResponse<List<BillingInfoBean>>> getBillingByLocalityId(int localityId, String date) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getBillingByLocalityId(localityId,date);
    }

    @Override
    public Observable<BaseResponse<PageBean<BillingInfoBean>>> getBillingByObjectId(int objectId, String date, int pageIndex, int pageSize) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getBillingByObjectId(objectId,date,pageIndex,pageSize);
    }

    @Override
    public Observable<BaseResponse<List<HeatSeasonBean>>> getHeatSeason(int localityId) {
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        return apiservice.getBillingSeason(localityId);
    }
}
