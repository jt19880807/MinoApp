package com.minoapp.base;

/**
 * Created by Devin on 2017/6/26.
 */

public interface BaseView {
    void showLoading();
    void showError(String msg);
    void dismissLoading();
}
