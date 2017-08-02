package com.minoapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.minoapp.R;
import com.minoapp.base.BaseActivity;

public class MeterReadingActivity extends BaseActivity {

    @Override
    protected String getTAG() {
        return MeterReadingActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reading;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }
}
