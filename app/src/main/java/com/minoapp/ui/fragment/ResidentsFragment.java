package com.minoapp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.minoapp.R;
import com.minoapp.adapter.MeterListAdapter;
import com.minoapp.adapter.ResidentsAdapter;
import com.minoapp.base.BaseFragment;
import com.minoapp.common.Constant;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.bean.ResidentBean;
import com.minoapp.data.model.ResidentModel;
import com.minoapp.presenter.ResidentsPresenter;
import com.minoapp.presenter.contract.ResidentsContract;
import com.minoapp.ui.activity.ObjectActivity;
import com.minoapp.ui.activity.ResidentDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ResidentsFragment extends BaseFragment implements ResidentsContract.ResidentsView,BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.recyc_residents)
    RecyclerView recycResidents;

    ResidentsPresenter presenter;
    private int pageIndex=1;
    private int pageSize=20;
    private ResidentsAdapter adapter;

    public ResidentsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<ResidentBean> beanList=new ArrayList<>();
        showResidents(beanList);
        ResidentModel model=new ResidentModel();
        presenter=new ResidentsPresenter(model,this);
        //暂时写死
        presenter.getResidents(1341,pageIndex,pageSize);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_residents;
    }

    @Override
    public void showData(PageBean<ResidentBean> pageBean) {
        adapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore()) {
            pageIndex++;
        }
        adapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        adapter.loadMoreComplete();
    }

    private void showResidents(List<ResidentBean> residentBeanList) {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter=new ResidentsAdapter(residentBeanList);
        adapter.setOnLoadMoreListener(this);

        recycResidents.setLayoutManager(layoutManager);
        recycResidents.setAdapter(adapter);
        recycResidents.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ResidentBean residentBean=(ResidentBean) adapter.getData().get(position);
                Bundle bundle=new Bundle();
                bundle.putInt(Constant.LOCALITY_ID,residentBean.getId());
                bundle.putString(Constant.LOCALITY_NUMBER,residentBean.getNumber());
                openActivity(ResidentDetailActivity.class,bundle);
            }
        });

    }

    @Override
    public void onLoadMoreRequested() {
        //Toast.makeText(getActivity(), "下一页", Toast.LENGTH_SHORT).show();
        //mAdapter.setEnableLoadMore(true);
        presenter.getResidents(1341,pageIndex ,pageSize);
    }
}
