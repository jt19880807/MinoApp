package com.minoapp.ui.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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
import com.minoapp.presenter.contract.MeterContract;
import com.minoapp.presenter.contract.IMeterModel;
import com.minoapp.ui.activity.MeterReadingActivity;
import com.minoapp.ui.activity.ObjectDetaileActivity;

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
    ProgressDialog progressDialog;
    int objectId = 0;
    public TempFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog=new ProgressDialog(getActivity());
        showTemp();
        IMeterModel model=new MeterModel();
        presenter=new MeterPresenter(model,this);
        //暂时写死
        presenter.getTempByObjectId(objectId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_temp;
    }

    @Override
    public void showHeatMeter(List<HeatMeterBean> beanList) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        objectId = ((ObjectDetaileActivity) context).setObjectId();
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
        recycTemp.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                MeterBean meterBean=(MeterBean)adapter.getData().get(position);
                Bundle bundle=new Bundle();
                bundle.putString(Constant.METER_TYPE,"3");
                bundle.putInt(Constant.METER_ID,meterBean.getID());
                openActivity(MeterReadingActivity.class,bundle);
            }
        });
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
