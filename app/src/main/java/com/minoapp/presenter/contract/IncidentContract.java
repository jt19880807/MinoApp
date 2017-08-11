package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.IncidentBean;
import com.minoapp.data.bean.PageBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/8/11.
 */

public interface IncidentContract {
    interface View extends BaseView {
        void showIncidents(PageBean<IncidentBean> pageBean);
        void onLoadMoreComplete();
    }
    interface IincidentModel{
        Observable<BaseResponse<PageBean<IncidentBean>>> getIncidents(int objectId,int pageIndex,int pageSize);
    }
}
