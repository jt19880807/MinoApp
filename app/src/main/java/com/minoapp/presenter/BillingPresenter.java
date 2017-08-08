package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.BillingInfoBean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.presenter.contract.BillingContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/8/8.
 */

public class BillingPresenter extends BasePresenter<BillingContract.IBillingModel,BillingContract.BillingView> {
    public BillingPresenter(BillingContract.IBillingModel iBillingModel, BillingContract.BillingView billingView) {
        super(iBillingModel, billingView);
    }

    public void getBillingByLocalityId(int localityId, String date){

        Observer observer=new ProgressSubcriber<BillingInfoBean>(context, view) {
            @Override
            public void onNext(@NonNull BillingInfoBean bean) {
                view.showBilling(bean);
            }
        };

        model.getBillingByLocalityId(localityId,date)
                .compose(RxHttpReponseCompat.<BillingInfoBean>compatResult())
                .subscribe(observer);
    }
}
