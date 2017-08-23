package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.AreaBean;
import com.minoapp.data.bean.HeatSeasonBean;
import com.minoapp.presenter.contract.AreaContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/8/16.
 */

public class AreaPresenter extends BasePresenter<AreaContract.IAreaModel,AreaContract.AreaView> {
    public AreaPresenter(AreaContract.IAreaModel iAreaModel, AreaContract.AreaView areaView) {
        super(iAreaModel, areaView);
    }

    public void getAreasByCustomerId(int customerId){
        Observer observer=new ProgressSubcriber<List<AreaBean>>(context,view) {

            @Override
            public void onNext(@NonNull List<AreaBean> areaBeanList) {
                view.showAreas(areaBeanList);
            }
        };

        model.getAreas(customerId)
                .compose(RxHttpReponseCompat.<List<AreaBean>>compatResult())
                .subscribe(observer);

    }
}
