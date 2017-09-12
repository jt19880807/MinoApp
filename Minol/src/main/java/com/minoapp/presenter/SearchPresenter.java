package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.AreaBean;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.HeatStationBean;
import com.minoapp.presenter.contract.SearchContact;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/9/12.
 */

public class SearchPresenter extends BasePresenter<SearchContact.SearchModel,SearchContact.SearchView> {

    public SearchPresenter(SearchContact.SearchModel searchModel, SearchContact.SearchView searchView) {
        super(searchModel, searchView);
    }

    public void getCustomerBeans(int userId){
        Observer observer=new ProgressSubcriber<List<CustomerBean>>(context, view) {
            @Override
            public void onNext(@NonNull List<CustomerBean> beanList) {
                view.showCustomers(beanList);
            }
        };
        model.getCustomerBeans(userId)
                .compose(RxHttpReponseCompat.<List<CustomerBean>>compatResult())
                .subscribe(observer);
    }
    public void getAreaBeans(int customerId){
        Observer observer=new ProgressSubcriber<List<AreaBean>>(context, view) {
            @Override
            public void onNext(@NonNull List<AreaBean> beanList) {
                view.showAreas(beanList);
            }
        };
        model.getAreaBeans(customerId)
                .compose(RxHttpReponseCompat.<List<AreaBean>>compatResult())
                .subscribe(observer);
    }
    public void getHeatStationBeans(int userId){
        Observer observer=new ProgressSubcriber<List<HeatStationBean>>(context, view) {
            @Override
            public void onNext(@NonNull List<HeatStationBean> beanList) {
                view.showHeatStations(beanList);
            }
        };
        model.getHeatStationBeans(userId)
                .compose(RxHttpReponseCompat.<List<HeatStationBean>>compatResult())
                .subscribe(observer);
    }
}
