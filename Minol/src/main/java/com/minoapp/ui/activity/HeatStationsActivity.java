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
import com.github.promeg.pinyinhelper.Pinyin;
import com.minoapp.R;
import com.minoapp.adapter.CustomerListAdapter;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.common.utils.ACache;
import com.minoapp.data.bean.Customer;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.CustomerSectionEntity;
import com.minoapp.data.bean.UserBean;
import com.minoapp.data.model.CustomerModel;
import com.minoapp.presenter.CustomerPresenter;
import com.minoapp.presenter.contract.CustomerContract;
import com.minoapp.ui.widget.SideBar.CityBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

public class HeatStationsActivity extends BaseActivity implements CustomerContract.CustomerView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recy_heatstations)
    RecyclerView recyHeatstations;
    ProgressDialog dialog;
    private CustomerPresenter presenter;
    private ProgressDialog progressDialog;
    CustomerListAdapter adapter;
    LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
    List<CustomerSectionEntity> customerBeenList;
    List<CityBean> mDatas;
    UserBean userBean;
    @Override
    protected String getTAG() {
        return HeatStationsActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_heat_stations;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
        initToolbar();
        init();
        progressDialog=new ProgressDialog(this);
        CustomerContract.ICustomerModel model=new CustomerModel();
        presenter=new CustomerPresenter(model,this);
        presenter.getAllHeatStations(userBean.getID());
    }
    private void initToolbar(){
        toolbar.setTitle("换热站");
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void init() {
        ACache aCache=ACache.get(this);
        userBean= (UserBean) aCache.getAsObject(Constant.USER);
        adapter=new CustomerListAdapter(R.layout.customer_item,R.layout.hca_reading_header,null);
        recyHeatstations.setLayoutManager(layoutManager);
        recyHeatstations.setAdapter(adapter);
        recyHeatstations.addOnItemTouchListener(new OnItemClickListener() {
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
        dialog.show();
    }

    @Override
    public void showError(String msg) {
        dismissLoading();
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        if (dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public void showData(List<Customer> customerBeen) {
        customerBeenList=getCustomerSectionEntitys(customerBeen);
        adapter.addData(customerBeenList);
    }

    @Override
    public void setCustomerBeans(List<CustomerBean> customerBeen) {

    }
    private List<CustomerSectionEntity> getCustomerSectionEntitys(List<Customer> customerBeen){
        List<CustomerSectionEntity> customerSectionEntities=new ArrayList<>();
        mDatas=new ArrayList<>();
        CityBean cityBean;
        CustomerSectionEntity customerSectionEntity;
        String city="";
        if (customerBeen.size()>0){
            for (Customer c :customerBeen) {
                city=c.getAddress().split(" ")[1];
                customerSectionEntity=new CustomerSectionEntity(true,c.getAddress(),city);
                cityBean=new CityBean(c.getAddress().split(" ")[1]);
                mDatas.add(cityBean);
                customerSectionEntities.add(customerSectionEntity);
                if (c.getData().size()>0){
                    for (CustomerBean cb :c.getData()) {
                        customerSectionEntity=new CustomerSectionEntity(cb,city);
                        customerSectionEntities.add(customerSectionEntity);
                        cityBean=new CityBean(c.getAddress().split(" ")[1]);
                        mDatas.add(cityBean);
                    }
                }
            }
        }
        return sortCustomer(customerSectionEntities);

    }

    private List<CustomerSectionEntity> sortCustomer(List<CustomerSectionEntity> customerSectionEntities) {
        for (CustomerSectionEntity cu:customerSectionEntities) {
            StringBuilder pySb = new StringBuilder();
            String city=cu.getCity();
            for (int i=0;i<city.length();i++){
                pySb.append(Pinyin.toPinyin(city.charAt(i)));
            }
            cu.setPyCity(pySb.toString());
        }
        Collections.sort(customerSectionEntities, new Comparator<CustomerSectionEntity>() {
            @Override
            public int compare(CustomerSectionEntity o1, CustomerSectionEntity o2) {
                return o1.getPyCity().compareTo(o2.getPyCity());
            }
        });
        return customerSectionEntities;
    }
}
