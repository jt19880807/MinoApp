package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ErrorHandlerSubscriber;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.BillingInfoBean;
import com.minoapp.data.bean.HeatSeasonBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.presenter.contract.BillingContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/8/8.
 */

public class BillingPresenter extends BasePresenter<BillingContract.IBillingModel,BillingContract.BillingView> {
    public BillingPresenter(BillingContract.IBillingModel iBillingModel, BillingContract.BillingView billingView) {
        super(iBillingModel, billingView);
    }

    /**
     * 获取当前住户的账单
     * @param localityId
     * @param date
     */
    public void getBillingByLocalityId(int localityId, String date){

        Observer observer=new ProgressSubcriber<List<BillingInfoBean>>(context, view) {
            @Override
            public void onNext(@NonNull List<BillingInfoBean> bean) {
                view.showLocatityBilling(bean);
            }
        };

        model.getBillingByLocalityId(localityId,date)
                .compose(RxHttpReponseCompat.<List<BillingInfoBean>>compatResult())
                .subscribe(observer);
    }

    /**
     * 获取当前项目下的账单(分页)
     * @param objectId
     * @param date
     * @param pageIndex
     * @param pageSize
     */
    public void getBillingByObjectId(int objectId,String date,int pageIndex,int pageSize){

        Observer observer;
        if(pageIndex==1) {
            observer=new ProgressSubcriber<PageBean<BillingInfoBean>>(context, view) {
                @Override
                public void onNext(@NonNull PageBean<BillingInfoBean> pageBean) {
                    view.showObjectBilling(pageBean);
                }
            };
        }
        else {

            observer= new ErrorHandlerSubscriber<PageBean<BillingInfoBean>>(context) {
                @Override
                public void onNext(@NonNull PageBean<BillingInfoBean> pageBean) {
                    view.showObjectBilling(pageBean);
                }

                @Override
                public void onComplete() {
                    view.onLoadMoreComplete();
                }
            };
        }
        model.getBillingByObjectId(objectId,date,pageIndex,pageSize)
                .compose(RxHttpReponseCompat.<PageBean<BillingInfoBean>>compatResult())
                .subscribe(observer);
    }

    /**
     * 根据住户获取供暖季信息
     * @param localityId
     */
    public void getHeatSeason(int localityId,String type){
        Observer observer=new ProgressSubcriber<List<HeatSeasonBean>>(context, view) {
            @Override
            public void onNext(@NonNull List<HeatSeasonBean> bean) {
                view.showHeatSeason(bean);
            }
        };

        model.getHeatSeason(localityId,type)
                .compose(RxHttpReponseCompat.<List<HeatSeasonBean>>compatResult())
                .subscribe(observer);
    }

}

