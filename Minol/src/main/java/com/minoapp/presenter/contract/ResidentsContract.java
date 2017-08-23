package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.ObjectBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.bean.ResidentBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/7/17.
 */

public interface ResidentsContract {
    public interface ResidentsView extends BaseView {
        void showData(PageBean<ResidentBean> pageBean);
        void showData(ResidentBean residentBeen);
        void onLoadMoreComplete();
    }

    public interface IResidentsModel{

        Observable<BaseResponse<PageBean<ResidentBean>>> getResidents(int objectID, int pageIndex, int pageSize);
        Observable<BaseResponse<ResidentBean>> getResidents(int localityId);

    }
}
