package com.minoapp.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.minoapp.R;
import com.minoapp.adapter.CustomerListAdapter;
import com.minoapp.api.ApiService;
import com.minoapp.api.RetrofitClient;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.data.bean.Customer;
import com.minoapp.data.model.CustomerModel;
import com.minoapp.presenter.CustomerPresenter;
import com.minoapp.presenter.contract.CustomerContract;

import java.util.List;

import butterknife.BindView;

public class CustomerActivity extends BaseActivity implements CustomerContract.CustomerView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vw_customer)
    RecyclerView vwCustomer;

    private CustomerPresenter presenter;

    private ProgressDialog progressDialog;

    @Override
    protected String getTAG() {
        return CustomerActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.setTitle("热力公司");
        setSupportActionBar(toolbar);
        progressDialog=new ProgressDialog(this);
        ApiService apiservice= RetrofitClient.getInstance().getApiService();
        CustomerContract.ICustomerModel model=new CustomerModel(apiservice);
        presenter=new CustomerPresenter(model,this);
        presenter.getAllCustomers();
    }

    public void showCustomers(List<Customer> customers) {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        CustomerListAdapter adapter=new CustomerListAdapter(customers,CustomerActivity.this);
        //为RecyclerView子项设置点击事件
        adapter.setOnItemClickListener(new CustomerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object item) {
                Customer c=(Customer)item;
                Bundle bundle=new Bundle();
                bundle.putInt(Constant.Customer_ID,c.getID());
                bundle.putString(Constant.Customer_Name,c.getName());
                openActivity(ObjectActivity.class,bundle);
                //Toast.makeText(CustomerActivity.this, c.getID()+"", Toast.LENGTH_SHORT).show();
            }
        });
        vwCustomer.setLayoutManager(layoutManager);
        vwCustomer.setAdapter(adapter);
    }


    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showData(List<Customer> customers) {
        showCustomers(customers);
    }
}
