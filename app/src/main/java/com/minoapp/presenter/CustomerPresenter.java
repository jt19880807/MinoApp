package com.minoapp.presenter;

import android.util.Log;

import com.minoapp.base.BasePresenter;
import com.minoapp.base.BaseResponse;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.data.bean.Customer;
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

    public void getAllCustomers(){
        Observable<BaseResponse<List<Customer>>> observable=model.getCustomers();

        //model.getCustomers().compose(RxHttpReponseCompat.<List<Customer>>compatResult())
        observable.compose(RxHttpReponseCompat.<List<Customer>>compatResult())
                .subscribe(new Observer<List<Customer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        view.showLoading();
                    }
                    @Override
                    public void onNext(@NonNull List<Customer> customers) {
                        view.showData(customers);

                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("ErrorHandlerSubscriber",e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                        view.dismissLoading();
                    }
                });
    }
}
