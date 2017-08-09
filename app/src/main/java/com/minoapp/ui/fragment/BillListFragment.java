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

import com.minoapp.R;
import com.minoapp.adapter.BillingInfoAdapter;
import com.minoapp.base.BaseFragment;
import com.minoapp.data.bean.BillingInfoBean;
import com.minoapp.data.model.BillingInfoModel;
import com.minoapp.presenter.BillingPresenter;
import com.minoapp.presenter.contract.BillingContract;
import com.minoapp.ui.activity.ResidentDetailActivity;
import com.minoapp.ui.widget.CustomDatePicker;
import com.minoapp.ui.widget.CustomYearPicker;

import java.text.SimpleDateFormat;
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
public class BillListFragment extends BaseFragment implements BillingContract.BillingView,View.OnClickListener {


    @BindView(R.id.recy_billing)
    RecyclerView recyBilling;

    BillingPresenter presenter;
    BillingInfoAdapter billingInfoAdapter;
    ProgressDialog progressDialog;
    int localityID = 0;
    String date = "2016-11-15";
    @BindView(R.id.tv_billing_date)
    TextView tvBillingDate;
    @BindView(R.id.tv_billing_search)
    TextView tvBillingSearch;
    private CustomYearPicker customYearPicker;

    public BillListFragment() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatePicker();
        progressDialog = new ProgressDialog(getActivity());
        showBillingInfo();
        BillingContract.IBillingModel model = new BillingInfoModel();
        presenter = new BillingPresenter(model, this);
        presenter.getBillingByLocalityId(localityID, date);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        localityID = ((ResidentDetailActivity) context).setLocalityID();
    }


    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        Calendar calendar = Calendar.getInstance();
        tvBillingDate.setText(sdf.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, -1);
        String now = sdf2.format(new Date());
        //tvHcaReadingStartdate.setText(sdf.format(calendar.getTime()));


        customYearPicker = new CustomYearPicker(getActivity(), new CustomYearPicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvBillingDate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", null); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        //customYearPicker.showYear(true); // 不显示时和分
        customYearPicker.setIsLoop(false); // 不允许循环滚动



        tvBillingDate.setOnClickListener(this);
        //tvHcaReadingEnddate.setOnClickListener(this);
    }

    private void showBillingInfo() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        billingInfoAdapter = new BillingInfoAdapter(null);
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
    public void showBilling(List<BillingInfoBean> bean) {
        billingInfoAdapter.addData(bean);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.tv_billing_date){
            customYearPicker.show(tvBillingDate.getText().toString());
        }
    }
}
