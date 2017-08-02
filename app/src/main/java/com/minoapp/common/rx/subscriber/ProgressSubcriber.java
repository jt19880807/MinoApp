package com.minoapp.common.rx.subscriber;

import android.content.Context;

import com.minoapp.base.BaseException;
import com.minoapp.base.BaseView;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Devin on 2017/7/4.
 */

public abstract class ProgressSubcriber<T> extends ErrorHandlerSubscriber<T> {
    private BaseView baseView;

    public ProgressSubcriber(Context context, BaseView baseView) {
        super(context);
        this.baseView = baseView;
    }

    public boolean isShowProgress(){
        return true;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if(isShowProgress()){
            baseView.showLoading();
        }
    }

    @Override
    public void onComplete() {
        baseView.dismissLoading();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        BaseException baseException=rxErrorHandler.handleError(e);
        baseView.showError(baseException.getDisplayMessage());
    }
}
