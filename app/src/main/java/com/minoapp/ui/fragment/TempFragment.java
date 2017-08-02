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
public class TempFragment extends BaseFragment implements MeterContract.HeatMeterView {


    @BindView(R.id.recyc_temp)
    RecyclerView recycTemp;

    MeterPresenter presenter;
    MeterListAdapter<MeterBean> adapter;

    public TempFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showTemp();
        IMeterModel model=new MeterModel();
        presenter=new MeterPresenter(model,this);
        //暂时写死
        presenter.getTempByObjectId(1341);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_temp;
    }

    @Override
    public void showHeatMeter(List<HeatMeterBean> beanList) {

    }

    @Override
    public void showTemp(List<MeterBean> beanList) {
        adapter.addData(beanList);
    }

    @Override
    public void showHCA(List<HCABean> beanList) {

    }

    @Override
    public void onLoadMoreComplete() {

    }

    private void showTemp() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter= new MeterListAdapter<>(null);
        recycTemp.setLayoutManager(layoutManager);
        recycTemp.setAdapter(adapter);
    }

}
