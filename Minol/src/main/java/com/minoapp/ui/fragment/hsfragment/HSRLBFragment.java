package com.minoapp.ui.fragment.hsfragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.minoapp.R;
import com.minoapp.base.BaseFragment;
import com.minoapp.common.Constant;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HSRLBFragment extends BaseFragment {

    int meterType = 0;
    @BindView(R.id.hs_tv)
    TextView hsTv;
    public HSRLBFragment(){

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            meterType=bundle.getInt(Constant.HEATSTATION_METER_TYPE);
//        }
        hsTv.setText("热量表");
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hsheat_meters;
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
