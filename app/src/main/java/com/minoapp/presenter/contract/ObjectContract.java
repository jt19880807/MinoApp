package com.minoapp.presenter.contract;


import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.ObjectBean;
import com.minoapp.data.bean.PageBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/7/13.
 */

public interface ObjectContract {

    public interface ObjectView extends BaseView{
        void showData(PageBean<ObjectBean> pageBean);
        void onLoadMoreComplete();
    }

    public interface IObjectModel{

        Observable<BaseResponse<PageBean<ObjectBean>>> getAllObjects(int userId, int customerId,int pageIndex,int pageSize);

    }

}
