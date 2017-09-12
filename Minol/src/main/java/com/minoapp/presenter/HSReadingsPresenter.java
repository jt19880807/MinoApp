package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ErrorHandlerSubscriber;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.BuildMeterReadingBean;
import com.minoapp.data.bean.HSMeterReadingBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.presenter.contract.HSReadingsContract;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/9/8.
 */

public class HSReadingsPresenter extends BasePresenter<HSReadingsContract.IHSReadingModel,HSReadingsContract.HSReadingsView> {
    public HSReadingsPresenter(HSReadingsContract.IHSReadingModel ihsReadingModel, HSReadingsContract.HSReadingsView hsReadingsView) {
        super(ihsReadingModel, hsReadingsView);
    }

    public void getHSReadings(int meterId, String startDate, String endDate, int pageIndex, int pageSize, int meterType){
        Observer observer;
        if(pageIndex==1) {
            observer=new ProgressSubcriber<PageBean<HSMeterReadingBean>>(context, view) {
                @Override
                public void onNext(@NonNull PageBean<HSMeterReadingBean> pageBean) {
                    view.showReadings(pageBean);
                }
            };
        }
        else {

            observer= new ErrorHandlerSubscriber<PageBean<HSMeterReadingBean>>(context) {
                @Override
                public void onNext(@NonNull PageBean<HSMeterReadingBean> pageBean) {
                    view.showReadings(pageBean);
                }

                @Override
                public void onComplete() {
                    view.onLoadMoreComplete();
                }
            };
        }
        model.getHSReadings(meterId,startDate,endDate,pageIndex,pageSize,meterType)
                .compose(RxHttpReponseCompat.<PageBean<HSMeterReadingBean>>compatResult())
                .subscribe(observer);

    }

    public void getHSLastReadings(int meterId,int meterType){
        Observer observer=new ProgressSubcriber<PageBean<HSMeterReadingBean>>(context, view) {
            @Override
            public void onNext(@NonNull PageBean<HSMeterReadingBean> pageBean) {
                view.showLastReadings(pageBean);
            }
        };

        model.getHSLastReadings(meterId,meterType)
                .compose(RxHttpReponseCompat.<PageBean<HSMeterReadingBean>>compatResult())
                .subscribe(observer);
    }
}
