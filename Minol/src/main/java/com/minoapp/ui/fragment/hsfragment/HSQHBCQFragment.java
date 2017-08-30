package com.minoapp.ui.fragment.hsfragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.minoapp.R;
import com.minoapp.adapter.MeterListAdapter;
import com.minoapp.base.BaseFragment;
import com.minoapp.common.Constant;
import com.minoapp.data.bean.HCABean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.data.bean.MeterBean;
import com.minoapp.data.model.MeterModel;
import com.minoapp.presenter.MeterPresenter;
import com.minoapp.presenter.contract.IMeterModel;
import com.minoapp.presenter.contract.MeterContract;
import com.minoapp.ui.activity.HSMeterReadingsActivity;
import com.minoapp.ui.activity.HeatStationMetersActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HSQHBCQFragment extends BaseFragment implements MeterContract.HeatMeterView {

    public HSQHBCQFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    MeterPresenter presenter;
    private MeterListAdapter<HeatMeterBean> adapter;
    ProgressDialog progressDialog;
    int heatStationId=0;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {

        progressDialog=new ProgressDialog(getActivity());
        showHeatMeters();
        IMeterModel model = new MeterModel();
        presenter = new MeterPresenter(model, this);
        presenter.getHeatStationMeters(heatStationId, Constant.HEATSTATION_METER_QHBCQ);
    }

    private void showHeatMeters(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter=new MeterListAdapter<HeatMeterBean>(null);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);
        recyclerview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                HeatMeterBean heatMeterBean=(HeatMeterBean) adapter.getData().get(position);
                Bundle bundle=new Bundle();
                bundle.putInt(Constant.METER_TYPE,Constant.HEATSTATION_METER_QHBCQ);
                bundle.putInt(Constant.METER_ID,heatMeterBean.getID());
                openActivity(HSMeterReadingsActivity.class,bundle);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        heatStationId=((HeatStationMetersActivity)context).setHeatStationId();
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

    @Override
    public void showHeatMeter(List<HeatMeterBean> beanList) {

    }

    @Override
    public void showTemp(List<MeterBean> beanList) {

    }

    @Override
    public void showHCA(List<HCABean> beanList) {

    }

    @Override
    public void onLoadMoreComplete() {

    }

}
