package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.AreaBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/8/16.
 */

public interface AreaContract {
    interface AreaView extends BaseView {
        void showAreas(List<AreaBean> areaBeanList);
    }
    interface IAreaModel{
        Observable<BaseResponse<List<AreaBean>>> getAreas(int customerId);

    }
}
