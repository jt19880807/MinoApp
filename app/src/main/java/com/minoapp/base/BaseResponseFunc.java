package com.minoapp.base;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Devin on 2017/6/14.
 */

public class BaseResponseFunc<T> implements Function<BaseResponse<T>,Observable<T>> {

    @Override
    public Observable<T> apply(@NonNull BaseResponse<T> tBaseResponse) throws Exception {
//        遇到非200统一处理，将BaseResponse转换成想要的对象
//        if (tBaseResponse.()!=200)
//            return Observable.error(new Throwable(tBaseResponse.getStatus_msg()));
//        else
//            return  Observable.just(tBaseResponse.getData());
        return null;
    }
};
