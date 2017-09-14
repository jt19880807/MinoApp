package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.AreaBean;
import com.minoapp.data.bean.PageBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/8/16.
 */

public interface AreaContract {
    interface AreaView extends BaseView {
        //void showAreas(PageBean<AreaBean> areaBeanList);
        void showAreas(List<AreaBean> areaBeanList);
        //void onLoadMoreComplete();
    }
    interface IAreaModel{
        //Observable<BaseResponse<PageBean<AreaBean>>> getAreas(int customerId,int pageIndex,int pageSize);
        Observable<BaseResponse<List<AreaBean>>> getAreas(int customerId);

    }
}
