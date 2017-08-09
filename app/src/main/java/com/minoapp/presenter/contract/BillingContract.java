package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.BillingInfoBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/8/8.
 */

public interface BillingContract {
    interface BillingView extends BaseView{
        void showBilling(List<BillingInfoBean> bean);
    }
    interface IBillingModel{
        Observable<BaseResponse<List<BillingInfoBean>>> getBillingByLocalityId(int localityId, String date);
    }
}
