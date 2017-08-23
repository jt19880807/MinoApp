package com.minoapp.ui.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.minoapp.R;
import com.minoapp.adapter.MeterListAdapter;
import com.minoapp.base.BaseFragment;
import com.minoapp.data.bean.HCABean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.data.bean.MeterBean;
import com.minoapp.data.model.MeterModel;
import com.minoapp.presenter.MeterPresenter;
import com.minoapp.presenter.contract.IMeterModel;
import com.minoapp.presenter.contract.MeterContract;
import com.minoapp.ui.activity.ResidentDetailActivity;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HCAListFragment extends BaseFragment implements MeterContract.HeatMeterView {


    @BindView(R.id.recyc_hcalist)
    RecyclerView recycHcalist;

    MeterPresenter presenter;
    MeterListAdapter<HCABean> adapter;

    ProgressDialog progressDialog;
    int localityID=0;
    public HCAListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog=new ProgressDialog(getActivity());
        showHCA();
        IMeterModel model=new MeterModel();
        presenter=new MeterPresenter(model,this);
        //Toast.makeText(getActivity(), localityID+"", Toast.LENGTH_SHORT).show();
        presenter.getHCAByLocalityId(localityID);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        localityID=((ResidentDetailActivity)context).setLocalityID();
    }

    private void showHCA() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter= new MeterListAdapter<>(null);
        recycHcalist.setLayoutManager(layoutManager);
        recycHcalist.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hcalist;
    }

    @Override
    public void showHeatMeter(List<HeatMeterBean> beanList) {

    }

    @Override
    public void showTemp(List<MeterBean> beanList) {

    }

    @Override
    public void showHCA(List<HCABean> beanList) {
        adapter.addData(beanList);
    }

    @Override
    public void onLoadMoreComplete() {

    }


    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void showError(String msg) {
        dismissLoading();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
