package com.minoapp.ui.fragment;


import android.app.ProgressDialog;
import android.content.Context;
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
import com.minoapp.ui.activity.ObjectDetaileActivity;
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
    ProgressDialog progressDialog;
    int objectId = 0;
    int localityId=0;
    public ResidentsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog=new ProgressDialog(getActivity());
        List<ResidentBean> beanList=new ArrayList<>();
        showResidents(beanList);
        ResidentModel model=new ResidentModel();
        presenter=new ResidentsPresenter(model,this);
        if ("住户".equals(userBean.getRoleName())){
            //普通住户 只显示当前住户信息
            presenter.getResidents(userBean.getLocalityId());
        }
        else {
            //管理员或者热力公司 显示所有的住户信息
            presenter.getResidents(objectId,pageIndex,pageSize);

        }

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        objectId = ((ObjectDetaileActivity) context).setObjectId();
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
    public void showData(List<ResidentBean> residentBeen) {
        adapter.addData(residentBeen);
        adapter.setEnableLoadMore(false);
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
                bundle.putInt(Constant.ISGAOZAO,residentBean.getHCACount()>0?1:0);
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
