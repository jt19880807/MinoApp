package com.minoapp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minoapp.R;
import com.minoapp.adapter.MeterListAdapter;
import com.minoapp.base.BaseFragment;
import com.minoapp.data.bean.HCABean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.data.bean.MeterBean;
import com.minoapp.data.model.MeterModel;
import com.minoapp.presenter.MeterPresenter;
import com.minoapp.presenter.contract.MeterContract;
import com.minoapp.presenter.contract.IMeterModel;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeatMeterFragment extends BaseFragment implements MeterContract.HeatMeterView {

    MeterPresenter presenter;
    private MeterListAdapter<HeatMeterBean> adapter;
    @BindView(R.id.recyc_heatmer)
    RecyclerView recycHeatmer;


    public HeatMeterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showHeatMeters();
        IMeterModel model = new MeterModel();
        presenter = new MeterPresenter(model, this);
        //暂时写死
        presenter.getHeatMetersByObjectId(1341);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_heat_meter;
    }

    @Override
    public void showHeatMeter(List<HeatMeterBean> beanList) {
        adapter.addData(beanList);
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

    private void showHeatMeters(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter=new MeterListAdapter<HeatMeterBean>(null);
        recycHeatmer.setLayoutManager(layoutManager);
        recycHeatmer.setAdapter(adapter);

    }
}
