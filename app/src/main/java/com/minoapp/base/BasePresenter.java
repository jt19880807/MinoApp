package com.minoapp.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Devin on 2017/6/27.
 */

public class BasePresenter<M,V extends BaseView> {
    protected M model;
    protected V view;
    protected Context context;

    public BasePresenter(M m, V v) {
        this.model = m;
        this.view = v;
        initContext();
    }
    private void initContext(){
        if(view instanceof Fragment)
            context=((Fragment)view).getActivity();
        else
            context=(Activity)view;
    }
}
