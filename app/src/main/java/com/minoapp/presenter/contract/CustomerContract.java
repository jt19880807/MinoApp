package com.minoapp.presenter.contract;


import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.Customer;
import com.minoapp.data.bean.CustomerBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/6/27.
 */

public interface CustomerContract {
    public interface CustomerView extends BaseView {
        public void showData(List<Customer> customerBeen);
    }
    public interface ICustomerModel{
        Observable<BaseResponse<List<Customer>>> getCustomers(String userId);
    }
}
