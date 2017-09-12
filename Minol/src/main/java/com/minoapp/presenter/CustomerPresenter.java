package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.Customer;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.HeatStation;
import com.minoapp.presenter.contract.CustomerContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/6/27.
 */

public class CustomerPresenter extends BasePresenter<CustomerContract.ICustomerModel,CustomerContract.CustomerView> {
    public CustomerPresenter(CustomerContract.ICustomerModel iCustomerModel, CustomerContract.CustomerView customerView) {
        super(iCustomerModel, customerView);
    }

    public void getAllCustomers(String userId){
        Observer observer=new ProgressSubcriber<List<Customer>>(context, view) {
            @Override
            public void onNext(@NonNull List<Customer> beanList) {
                view.showCustomers(beanList);
            }
        };
        model.getCustomers(userId)
                .compose(RxHttpReponseCompat.<List<Customer>>compatResult())
                .subscribe(observer);
    }

    public void getAllHeatStations(int userId){
        Observer observer=new ProgressSubcriber<List<HeatStation>>(context, view) {
            @Override
            public void onNext(@NonNull List<HeatStation> beanList) {
                view.showHeatStations(beanList);
            }
        };
        model.getHeatStations(userId)
                .compose(RxHttpReponseCompat.<List<HeatStation>>compatResult())
                .subscribe(observer);
    }
}
