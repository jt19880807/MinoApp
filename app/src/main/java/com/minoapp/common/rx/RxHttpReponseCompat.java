package com.minoapp.common.rx;

import com.minoapp.base.BaseResponse;
import com.minoapp.common.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxHttpReponseCompat {
    public static <T> ObservableTransformer<BaseResponse<T>,T> compatResult(){
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                return upstream.flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull final BaseResponse<T> tBaseResponse) throws Exception {
                        if (!tBaseResponse.success()) {
                            return  Observable.error(new ApiException(tBaseResponse.getCode(),tBaseResponse.getMsg()));
                        } else {//获取数据成功
                            return Observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> e) throws Exception {
                                    try{
                                        e.onNext(tBaseResponse.getData());
                                        e.onComplete();
                                    }
                                    catch (Exception e1){
                                        e.onError(e1);
                                    }
                                }
                            });
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
                //return null;
            }
        };
    }
}
