package com.minoapp.common.rx.subscriber;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.minoapp.base.BaseException;
import com.minoapp.common.rx.RxErrorHandler;
import com.minoapp.ui.activity.LoginActivity;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Devin on 2017/7/4.
 */

public abstract class ErrorHandlerSubscriber<T> implements Observer<T> {

    private Context mContext;
    protected RxErrorHandler rxErrorHandler=null;
    public ErrorHandlerSubscriber(Context context) {
        this.mContext = context;
        rxErrorHandler=new RxErrorHandler(mContext);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        BaseException baseException=rxErrorHandler.handleError(e);
        if (baseException==null){
            e.printStackTrace();
            Log.d("ErrorHandlerSubscriber",e.getMessage());
        }
        else {
            rxErrorHandler.showErrorMessage(baseException);
            if(baseException.getCode() == BaseException.ERROR_TOKEN){
                toLogin();
            }
        }
    }

    private void toLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
}
