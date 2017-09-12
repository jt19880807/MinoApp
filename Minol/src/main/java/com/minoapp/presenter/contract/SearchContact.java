package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.AreaBean;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.HeatStationBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/9/12.
 */

public interface SearchContact {
    interface SearchView extends BaseView {
        void showCustomers(List<CustomerBean> customerBeen);
        void showAreas(List<AreaBean> areaBeen);
        void showHeatStations(List<HeatStationBean> heatStationBeen);
    }
    interface SearchModel {
        Observable<BaseResponse<List<CustomerBean>>> getCustomerBeans(int userId);
        Observable<BaseResponse<List<AreaBean>>> getAreaBeans(int customerId);
        Observable<BaseResponse<List<HeatStationBean>>> getHeatStationBeans(int userId);

    }
}
