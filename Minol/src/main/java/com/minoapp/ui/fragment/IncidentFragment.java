package com.minoapp.ui.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.minoapp.R;
import com.minoapp.adapter.IncidentAdapter;
import com.minoapp.base.BaseFragment;
import com.minoapp.data.bean.IncidentBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.model.IncidentModel;
import com.minoapp.presenter.IncidentPresenter;
import com.minoapp.presenter.contract.IncidentContract;
import com.minoapp.ui.activity.ObjectDetaileActivity;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncidentFragment extends BaseFragment implements IncidentContract.View,BaseQuickAdapter.RequestLoadMoreListener {


    int objectId = 0;
    @BindView(R.id.recy_incidents)
    RecyclerView recyIncidents;
    IncidentPresenter presenter;
    IncidentAdapter adapter;
    ProgressDialog dialog;
    int pageIndex=1;
    int pageSize=10;
    public IncidentFragment() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dialog=new ProgressDialog(getActivity());
        IncidentContract.IincidentModel model=new IncidentModel();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyIncidents.setLayoutManager(layoutManager);
        adapter=new IncidentAdapter(R.layout.incidents_item);
        adapter.setOnLoadMoreListener(this,recyIncidents);
        adapter.setEmptyView(R.layout.emptyview,recyIncidents);

        recyIncidents.setAdapter(adapter);
        presenter=new IncidentPresenter(model,this);
        presenter.getIncidents(objectId,pageIndex,pageSize);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        objectId = ((ObjectDetaileActivity) context).setObjectId();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_incident;
    }

    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void showError(String msg) {
        dismissLoading();
        Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        if (dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public void showIncidents(PageBean<IncidentBean> pageBean) {
        adapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore())
            pageIndex++;
        adapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        adapter.loadMoreComplete();
    }


    @Override
    public void onLoadMoreRequested() {
        presenter.getIncidents(objectId,pageIndex,pageSize);
    }
}
