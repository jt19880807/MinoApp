package com.minoapp.presenter;

import com.minoapp.base.BasePresenter;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ErrorHandlerSubscriber;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.data.bean.ObjectBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.presenter.contract.ObjectContract;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/7/13.
 */

public class ObjectPresenter extends BasePresenter<ObjectContract.IObjectModel,ObjectContract.ObjectView> {
    public ObjectPresenter(ObjectContract.IObjectModel iObjectModel, ObjectContract.ObjectView objectView) {
        super(iObjectModel, objectView);
    }

    public void getObjects(int userID, int customerID, int pageIndex, int pageSize) {
        Observer observer;
        if(pageIndex==1) {
            observer=new ProgressSubcriber<PageBean<ObjectBean>>(context, view) {
                @Override
                public void onNext(@NonNull PageBean<ObjectBean> pageBean) {
                    view.showData(pageBean);
                }
            };
        }
        else {

            observer= new ErrorHandlerSubscriber<PageBean<ObjectBean>>(context) {
                @Override
                public void onNext(@NonNull PageBean<ObjectBean> pageBean) {
                    view.showData(pageBean);
                }

                @Override
                public void onComplete() {
                    view.onLoadMoreComplete();
                }
            };
        }

        model.getAllObjects(userID,customerID,pageIndex,pageSize)
                .compose(RxHttpReponseCompat.<PageBean<ObjectBean>>compatResult())
                .subscribe(observer);

    }
}
