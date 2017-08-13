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
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.CustomerSectionEntity;
import com.minoapp.data.model.CustomerModel;
import com.minoapp.presenter.CustomerPresenter;
import com.minoapp.presenter.contract.CustomerContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CustomerActivity extends BaseActivity implements CustomerContract.CustomerView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vw_customer)
    RecyclerView vwCustomer;

    private CustomerPresenter presenter;

    private ProgressDialog progressDialog;
    CustomerListAdapter adapter;

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
        presenter.getAllCustomers("1");
    }

    public void showCustomers(List<CustomerSectionEntity> customerBeen) {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter=new CustomerListAdapter(R.layout.hca_reading_header,
                R.layout.activity_customer,customerBeen);
        //为RecyclerView子项设置点击事件
//        adapter.setOnItemClickListener(new CustomerListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, Object item) {
//                CustomerBean c=(CustomerBean)item;
//                Bundle bundle=new Bundle();
//                bundle.putInt(Constant.Customer_ID,c.getID());
//                bundle.putString(Constant.Customer_Name,c.getName());
//                openActivity(ObjectActivity.class,bundle);
//                //Toast.makeText(CustomerActivity.this, c.getID()+"", Toast.LENGTH_SHORT).show();
//            }
//        });
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
    public void showData(List<Customer> customerBeen) {
        showCustomers(getCustomerSectionEntitys(customerBeen));
    }

    private List<CustomerSectionEntity> getCustomerSectionEntitys(List<Customer> customerBeen){
        List<CustomerSectionEntity> customerSectionEntities=new ArrayList<>();
        CustomerSectionEntity customerSectionEntity;
        if (customerBeen.size()>0){
            for (Customer c :customerBeen) {
                customerSectionEntity=new CustomerSectionEntity(true,c.getAddress());
                customerSectionEntities.add(customerSectionEntity);
                if (c.getDatas().size()>0){
                    for (CustomerBean cb :c.getDatas()) {
                        customerSectionEntity=new CustomerSectionEntity(cb);
                        customerSectionEntities.add(customerSectionEntity);
                    }
                }
            }
        }
        return customerSectionEntities;
    }
}
