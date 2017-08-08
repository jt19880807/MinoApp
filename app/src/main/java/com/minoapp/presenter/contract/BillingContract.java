package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.BillingInfoBean;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/8/8.
 */

public interface BillingContract {
    interface BillingView extends BaseView{
        void showBilling(BillingInfoBean bean);
    }
    interface IBillingModel{
        Observable<BaseResponse<BillingInfoBean>> getBillingByLocalityId(int localityId, String date);
    }
}
