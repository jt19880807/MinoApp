package com.minoapp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
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
        initToolbar();

        //setSupportActionBar(toolbar);
        progressDialog=new ProgressDialog(this);
        CustomerContract.ICustomerModel model=new CustomerModel();
        presenter=new CustomerPresenter(model,this);
        presenter.getAllCustomers("1");
    }

    private void initToolbar(){
        toolbar.setTitle("热力公司");
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.action_search){
                    startActivity(new Intent(CustomerActivity.this,SearchActivity.class));
                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showCustomers(List<CustomerSectionEntity> customerBeen) {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter=new CustomerListAdapter(R.layout.customer_item,R.layout.hca_reading_header,customerBeen);
        vwCustomer.setLayoutManager(layoutManager);
        vwCustomer.setAdapter(adapter);
        vwCustomer.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                CustomerSectionEntity c=(CustomerSectionEntity)adapter.getItem(position);
                if (!c.isHeader) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.Customer_ID, c.customerBean.getID());
                    bundle.putString(Constant.Customer_Name, c.customerBean.getName());
                    openActivity(AreaActivity.class, bundle);
                }
            }
        });
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

    @Override
    public void setCustomerBeans(List<CustomerBean> customerBeen) {

    }

    private List<CustomerSectionEntity> getCustomerSectionEntitys(List<Customer> customerBeen){
        List<CustomerSectionEntity> customerSectionEntities=new ArrayList<>();
        CustomerSectionEntity customerSectionEntity;
        if (customerBeen.size()>0){
            for (Customer c :customerBeen) {
                customerSectionEntity=new CustomerSectionEntity(true,c.getAddress());
                customerSectionEntities.add(customerSectionEntity);
                if (c.getData().size()>0){
                    for (CustomerBean cb :c.getData()) {
                        customerSectionEntity=new CustomerSectionEntity(cb);
                        customerSectionEntities.add(customerSectionEntity);
                    }
                }
            }
        }
        return customerSectionEntities;
    }
}
