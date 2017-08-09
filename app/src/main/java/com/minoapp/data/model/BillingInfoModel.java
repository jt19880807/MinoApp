package com.minoapp.data.model;

import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.BillingInfoBean;
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
}
