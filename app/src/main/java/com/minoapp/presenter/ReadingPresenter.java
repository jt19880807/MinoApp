package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ErrorHandlerSubscriber;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.BuildMeterReadingBean;
import com.minoapp.data.bean.HCAReading;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.bean.ReadingBean;
import com.minoapp.presenter.contract.IReadingModel;
import com.minoapp.presenter.contract.ReadingContract;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/7/28.
 */

public class ReadingPresenter extends BasePresenter<IReadingModel,ReadingContract.ReadingView> {
    public ReadingPresenter(IReadingModel iReadingModel, ReadingContract.ReadingView readingView) {
        super(iReadingModel, readingView);
    }

    public void getHCAReadings(int localityId, String startDate, String endDate, int pageIndex, int pageSize){
        Observer observer;
        if(pageIndex==1) {
            observer=new ProgressSubcriber<PageBean<HCAReading>>(context, view) {
                @Override
                public void onNext(@NonNull PageBean<HCAReading> pageBean) {
                    view.showHCAReadings(pageBean);
                }
            };
        }
        else {

            observer= new ErrorHandlerSubscriber<PageBean<HCAReading>>(context) {
                @Override
                public void onNext(@NonNull PageBean<HCAReading> pageBean) {
                    view.showHCAReadings(pageBean);
                }

                @Override
                public void onComplete() {
                    view.onLoadMoreComplete();
                }
            };
        }
        model.getHCAReadings(localityId,startDate,endDate,pageIndex,pageSize)
                .compose(RxHttpReponseCompat.<PageBean<HCAReading>>compatResult())
                .subscribe(observer);

    }

    /**
     * 获取当前房间的最新的热分配计的读数
     * @param localityId
     */
    public void getHCAReadings(int localityId){
        Observer observer=new ProgressSubcriber<PageBean<HCAReading>>(context, view) {
                @Override
                public void onNext(@NonNull PageBean<HCAReading> pageBean) {
                    view.showHCALastReadings(pageBean);
                }
            };
        model.getHCAReadings(localityId)
                .compose(RxHttpReponseCompat.<PageBean<HCAReading>>compatResult())
                .subscribe(observer);

    }



    public void getBuildMeterReadings(int meterId, String startDate, String endDate, int pageIndex, int pageSize){

        Observer observer;
        if(pageIndex==1) {
            observer=new ProgressSubcriber<PageBean<BuildMeterReadingBean>>(context, view) {
                @Override
                public void onNext(@NonNull PageBean<BuildMeterReadingBean> pageBean) {
                    view.showBReadings(pageBean);
                }
            };
        }
        else {

            observer= new ErrorHandlerSubscriber<PageBean<BuildMeterReadingBean>>(context) {
                @Override
                public void onNext(@NonNull PageBean<BuildMeterReadingBean> pageBean) {
                    view.showBReadings(pageBean);
                }

                @Override
                public void onComplete() {
                    view.onLoadMoreComplete();
                }
            };
        }
        model.getBuildMeterReadings(meterId,startDate,endDate,pageIndex,pageSize)
                .compose(RxHttpReponseCompat.<PageBean<BuildMeterReadingBean>>compatResult())
                .subscribe(observer);
    }

    /**
     * 获取当前楼栋大表的最新的读数
     * @param meterId
     * @param pageIndex
     * @param pageSize
     */
    public void getBuildMeterReadings(int meterId, int pageIndex, int pageSize){

        Observer observer;
        if(pageIndex==1) {
            observer=new ProgressSubcriber<PageBean<BuildMeterReadingBean>>(context, view) {
                @Override
                public void onNext(@NonNull PageBean<BuildMeterReadingBean> pageBean) {
                    view.showBLastReadings(pageBean);
                }
            };
        }
        else {

            observer= new ErrorHandlerSubscriber<PageBean<BuildMeterReadingBean>>(context) {
                @Override
                public void onNext(@NonNull PageBean<BuildMeterReadingBean> pageBean) {
                    view.showBLastReadings(pageBean);
                }

                @Override
                public void onComplete() {
                    view.onLoadMoreComplete();
                }
            };
        }
        model.getBuildMeterReadings(meterId,pageIndex,pageSize)
                .compose(RxHttpReponseCompat.<PageBean<BuildMeterReadingBean>>compatResult())
                .subscribe(observer);
    }

    public void getTempReadings(int meterId, String startDate, String endDate, int pageIndex, int pageSize){

        Observer observer;
        if(pageIndex==1) {
            observer=new ProgressSubcriber<PageBean<ReadingBean>>(context, view) {
                @Override
                public void onNext(@NonNull PageBean<ReadingBean> pageBean) {
                    view.showTempReading(pageBean);
                }
            };
        }
        else {

            observer= new ErrorHandlerSubscriber<PageBean<ReadingBean>>(context) {
                @Override
                public void onNext(@NonNull PageBean<ReadingBean> pageBean) {
                    view.showTempReading(pageBean);
                }

                @Override
                public void onComplete() {
                    view.onLoadMoreComplete();
                }
            };
        }
        model.getTempReadings(meterId,startDate,endDate,pageIndex,pageSize)
                .compose(RxHttpReponseCompat.<PageBean<ReadingBean>>compatResult())
                .subscribe(observer);
    }

    /**
     * 获取当前测温设备的最新的读数
     * @param meterId
     * @param pageIndex
     * @param pageSize
     */
    public void getTempReadings(int meterId, int pageIndex, int pageSize){

        Observer observer;
        if(pageIndex==1) {
            observer=new ProgressSubcriber<PageBean<ReadingBean>>(context, view) {
                @Override
                public void onNext(@NonNull PageBean<ReadingBean> pageBean) {
                    view.showTempLastReading(pageBean);
                }
            };
        }
        else {

            observer= new ErrorHandlerSubscriber<PageBean<ReadingBean>>(context) {
                @Override
                public void onNext(@NonNull PageBean<ReadingBean> pageBean) {
                    view.showTempLastReading(pageBean);
                }

                @Override
                public void onComplete() {
                    view.onLoadMoreComplete();
                }
            };
        }
        model.getTempReadings(meterId,pageIndex,pageSize)
                .compose(RxHttpReponseCompat.<PageBean<ReadingBean>>compatResult())
                .subscribe(observer);
    }
}
