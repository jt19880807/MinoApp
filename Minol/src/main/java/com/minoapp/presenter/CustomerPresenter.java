package com.minoapp.presenter;

import android.util.Log;

import com.minoapp.base.BasePresenter;
import com.minoapp.base.BaseResponse;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.Customer;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.presenter.contract.CustomerContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

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
                view.showData(beanList);
            }
        };
        model.getCustomers(userId)
                .compose(RxHttpReponseCompat.<List<Customer>>compatResult())
                .subscribe(observer);
    }

    public void getAllCustomerBeans(String userId){
        Observer observer=new ProgressSubcriber<List<CustomerBean>>(context, view) {
            @Override
            public void onNext(@NonNull List<CustomerBean> beanList) {
                view.setCustomerBeans(beanList);
            }
        };
        model.getCustomerBeans(userId)
                .compose(RxHttpReponseCompat.<List<CustomerBean>>compatResult())
                .subscribe(observer);
    }

    public void getAllHeatStations(int userId){
        Observer observer=new ProgressSubcriber<List<Customer>>(context, view) {
            @Override
            public void onNext(@NonNull List<Customer> beanList) {
                view.showData(beanList);
            }
        };
        model.getHeatStations(userId)
                .compose(RxHttpReponseCompat.<List<Customer>>compatResult())
                .subscribe(observer);
    }
}
