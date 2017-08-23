package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ErrorHandlerSubscriber;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.ObjectBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.bean.ResidentBean;
import com.minoapp.presenter.contract.ResidentsContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/7/17.
 */

public class ResidentsPresenter extends BasePresenter<ResidentsContract.IResidentsModel,ResidentsContract.ResidentsView> {
    public ResidentsPresenter(ResidentsContract.IResidentsModel iResidentsModel, ResidentsContract.ResidentsView residentsView) {
        super(iResidentsModel, residentsView);
    }

    public void getResidents(int objectID,int pageIndex, int pageSize){

        Observer observer;
        if(pageIndex==1) {
            observer=new ProgressSubcriber<PageBean<ResidentBean>>(context, view) {
                @Override
                public void onNext(@NonNull PageBean<ResidentBean> pageBean) {
                    view.showData(pageBean);
                }
            };
        }
        else {

            observer= new ErrorHandlerSubscriber<PageBean<ResidentBean>>(context) {
                @Override
                public void onNext(@NonNull PageBean<ResidentBean> pageBean) {
                    view.showData(pageBean);
                }

                @Override
                public void onComplete() {
                    view.onLoadMoreComplete();
                }
            };
        }
        model.getResidents(objectID,pageIndex,pageSize)
                .compose(RxHttpReponseCompat.<PageBean<ResidentBean>>compatResult())
                .subscribe(observer);
    }

    public void getResidents(int localityId) {
        Observer observer=new ProgressSubcriber<List<ResidentBean>>(context, view) {

            @Override
            public void onNext(@NonNull List<ResidentBean> residentBean) {
                view.showData(residentBean);
            }
        };
        model.getResidents(localityId)
                .compose(RxHttpReponseCompat.<List<ResidentBean>>compatResult())
                .subscribe(observer);

    }
}
