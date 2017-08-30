package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.HCABean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.data.bean.MeterBean;
import com.minoapp.presenter.contract.MeterContract;
import com.minoapp.presenter.contract.IMeterModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/7/19.
 */

public class MeterPresenter extends BasePresenter<IMeterModel,MeterContract.HeatMeterView> {
    public MeterPresenter(IMeterModel iMeterModel, MeterContract.HeatMeterView heatMeterView) {
        super(iMeterModel, heatMeterView);
    }

    public void getHeatMetersByObjectId(int objectId){
        Observer observer=new ProgressSubcriber<List<HeatMeterBean>>(context, view) {
            @Override
            public void onNext(@NonNull List<HeatMeterBean> beanList) {
                view.showHeatMeter(beanList);
            }
        };

        model.getBuildMetersByObjectId(objectId)
                .compose(RxHttpReponseCompat.<List<HeatMeterBean>>compatResult())
                .subscribe(observer);

    }

    public void getTemp(int id,int type) {
        Observer observer=new ProgressSubcriber<List<MeterBean>>(context, view) {
            @Override
            public void onNext(@NonNull List<MeterBean> beanList) {
                view.showTemp(beanList);
            }
        };

        model.getTemp(id,type)
                .compose(RxHttpReponseCompat.<List<MeterBean>>compatResult())
                .subscribe(observer);
    }

    public void getHCAByLocalityId(int localityId){
        Observer observer=new ProgressSubcriber<List<HCABean>>(context, view) {
            @Override
            public void onNext(@NonNull List<HCABean> beanList) {
                view.showHCA(beanList);
            }
        };
        model.getHCAByLocalityId(localityId)
                .compose(RxHttpReponseCompat.<List<HCABean>>compatResult())
                .subscribe(observer);
    }

    public void getHeatStationMeters(int hsid,int type){
        Observer observer=new ProgressSubcriber<List<HeatMeterBean>>(context, view) {
            @Override
            public void onNext(@NonNull List<HeatMeterBean> beanList) {
                view.showHeatMeter(beanList);
            }
        };

        model.getHeatStationMeters(hsid, type)
                .compose(RxHttpReponseCompat.<List<HeatMeterBean>>compatResult())
                .subscribe(observer);
    }

}
