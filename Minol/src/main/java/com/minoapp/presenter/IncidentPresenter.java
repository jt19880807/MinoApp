package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ErrorHandlerSubscriber;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.HCAReading;
import com.minoapp.data.bean.IncidentBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.presenter.contract.IncidentContract;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/8/11.
 */

public class IncidentPresenter extends BasePresenter<IncidentContract.IincidentModel,IncidentContract.View> {
    public IncidentPresenter(IncidentContract.IincidentModel iincidentModel, IncidentContract.View view) {
        super(iincidentModel, view);
    }

    public void getIncidents(int objectId,int pageIndex,int pageSize){

        Observer observer;
        if(pageIndex==1) {
            observer=new ProgressSubcriber<PageBean<IncidentBean>>(context, view) {
                @Override
                public void onNext(@NonNull PageBean<IncidentBean> pageBean) {
                    view.showIncidents(pageBean);
                }
            };
        }
        else {

            observer= new ErrorHandlerSubscriber<PageBean<IncidentBean>>(context) {
                @Override
                public void onNext(@NonNull PageBean<IncidentBean> pageBean) {
                    view.showIncidents(pageBean);
                }

                @Override
                public void onComplete() {
                    view.onLoadMoreComplete();
                }
            };
        }
        model.getIncidents(objectId,pageIndex,pageSize)
                .compose(RxHttpReponseCompat.<PageBean<IncidentBean>>compatResult())
                .subscribe(observer);
    }
}
