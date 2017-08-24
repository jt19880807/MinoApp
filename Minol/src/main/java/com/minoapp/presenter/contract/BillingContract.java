package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.BillingInfoBean;
import com.minoapp.data.bean.HeatSeasonBean;
import com.minoapp.data.bean.PageBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/8/8.
 */

public interface BillingContract {
    interface BillingView extends BaseView{
        void showLocatityBilling(List<BillingInfoBean> bean);
        void showObjectBilling(PageBean<BillingInfoBean> pageBean);
        void showHeatSeason(List<HeatSeasonBean> beanList);
        void onLoadMoreComplete();
    }
    interface IBillingModel{
        Observable<BaseResponse<List<BillingInfoBean>>> getBillingByLocalityId(int localityId, String date);
        Observable<BaseResponse<PageBean<BillingInfoBean>>> getBillingByObjectId(int objectId,String date,int pageIndex,int pageSize);
        Observable<BaseResponse<List<HeatSeasonBean>>> getHeatSeason(int id,int type);
    }
}
