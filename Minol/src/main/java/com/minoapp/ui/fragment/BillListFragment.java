package com.minoapp.ui.fragment;


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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.minoapp.R;
import com.minoapp.adapter.BillingInfoAdapter;
import com.minoapp.base.BaseFragment;
import com.minoapp.data.bean.BillingInfoBean;
import com.minoapp.data.bean.HeatSeasonBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.model.BillingInfoModel;
import com.minoapp.presenter.BillingPresenter;
import com.minoapp.presenter.contract.BillingContract;
import com.minoapp.ui.activity.ObjectDetaileActivity;
import com.minoapp.ui.activity.ResidentDetailActivity;
import com.minoapp.ui.widget.CustomDatePicker;
import com.minoapp.ui.widget.CustomYearPicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillListFragment extends BaseFragment implements BillingContract.BillingView,View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener  {


    @BindView(R.id.recy_billing)
    RecyclerView recyBilling;
    @BindView(R.id.tv_billing_date)
    TextView tvBillingDate;
    @BindView(R.id.tv_billing_search)
    TextView tvBillingSearch;

    BillingPresenter presenter;
    BillingInfoAdapter billingInfoAdapter;
    ProgressDialog progressDialog;
    int objectId=0;
    int pageIndex=1;
    int pageSize=10;
    String searDate="";

    private CustomYearPicker customYearPicker;
    List<HeatSeasonBean> seasonBeens=new ArrayList<>();
    public BillListFragment() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        showBillingInfo();
        tvBillingSearch.setOnClickListener(this);
        BillingContract.IBillingModel model = new BillingInfoModel();
        presenter = new BillingPresenter(model, this);
        if (userBean.getRoleName().equals("住户")){
            //根据住户ID获取供暖季
            presenter.getHeatSeason(userBean.getLocalityId(),2);
        }
        else {
            //根据项目ID获取供暖季
            presenter.getHeatSeason(objectId, 1);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        objectId=((ObjectDetaileActivity)context).setObjectId();
    }


    private void initDatePicker(ArrayList<String> seasons) {
        customYearPicker = new CustomYearPicker(getActivity(), new CustomYearPicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvBillingDate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", seasons); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customYearPicker.setIsLoop(false); // 不允许循环滚动
        tvBillingDate.setOnClickListener(this);
    }

    private void showBillingInfo() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        billingInfoAdapter = new BillingInfoAdapter(null);
        billingInfoAdapter.setOnLoadMoreListener(this,recyBilling);
        recyBilling.setLayoutManager(layoutManager);
        recyBilling.setAdapter(billingInfoAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bill_list;
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showLocatityBilling(List<BillingInfoBean> bean) {
        billingInfoAdapter.getData().clear();
        billingInfoAdapter.addData(bean);
        billingInfoAdapter.setEnableLoadMore(false);
    }

    @Override
    public void showObjectBilling(PageBean<BillingInfoBean> pageBean) {

        billingInfoAdapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore())
            pageIndex++;
        billingInfoAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void showHeatSeason(List<HeatSeasonBean> beanList) {
        seasonBeens=beanList;
        ArrayList<String> seasons=formatDate(seasonBeens);
        initDatePicker(seasons);
        if (seasons.size()>0){
            tvBillingDate.setText(seasonBeens.get(0).getSeasion().split("-")[0]);
        }else {
            tvBillingDate.setText("无");
        }
        searchBilling();
    }

    @Override
    public void onLoadMoreComplete() {
        billingInfoAdapter.loadMoreComplete();
    }

    private ArrayList<String> formatDate(List<HeatSeasonBean> heatSeasonBeens){
        ArrayList<String> result=new ArrayList<>();
        if (heatSeasonBeens.isEmpty()){
        }
        else {
            for (HeatSeasonBean h : heatSeasonBeens) {
                result.add(h.getSeasion().split("-")[0]);
            }
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.tv_billing_date){
            customYearPicker.show(tvBillingDate.getText().toString());
        }
        else if (v.getId()==R.id.tv_billing_search){
            pageIndex=1;

            searchBilling();
        }

    }

    private void searchBilling(){
        billingInfoAdapter.getData().clear();
        String date=tvBillingDate.getText().toString();
        if (seasonBeens.size()>0){
            for (HeatSeasonBean h : seasonBeens) {
                if (h.getSeasion().split("-")[0].equals(date)){
                    searDate=h.getSeasion().split("T")[0];
                    break;
                }
            }
            if (userBean.getRoleName().equals("住户")){
                presenter.getBillingByLocalityId(userBean.getLocalityId(),searDate);
            }else {
                presenter.getBillingByObjectId(objectId, searDate, pageIndex, pageSize);
            }
        }
        else {

        }


    }

    @Override
    public void onLoadMoreRequested() {
        presenter.getBillingByObjectId(objectId,searDate,pageIndex,pageSize);
    }
}
