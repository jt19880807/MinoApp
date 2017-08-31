package com.minoapp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
import com.minoapp.data.bean.HeatStation;
import com.minoapp.data.bean.UserBean;
import com.minoapp.data.model.CustomerModel;
import com.minoapp.presenter.CustomerPresenter;
import com.minoapp.presenter.contract.CustomerContract;
import com.minoapp.ui.widget.SideBar.CityBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

public class CustomerActivity extends BaseActivity implements CustomerContract.CustomerView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vw_customer)
    RecyclerView vwCustomer;
    @BindView(R.id.indexBar)
    com.minoapp.ui.widget.SideBar.SideBar indexBar;
    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;

    private CustomerPresenter presenter;
    private ProgressDialog progressDialog;
    CustomerListAdapter adapter;
    LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
    List<CustomerSectionEntity<CustomerBean>> customerBeenList;
    List<CityBean> mDatas;
    UserBean userBean;

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
        init();
        progressDialog=new ProgressDialog(this);
        CustomerContract.ICustomerModel model=new CustomerModel();
        presenter=new CustomerPresenter(model,this);
        presenter.getAllCustomers(userBean.getID()+"");
        //
    }

    private void initSideBar() {
        indexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(layoutManager)//设置RecyclerView的LayoutManager
                .setmSourceDatas(mDatas)
                .invalidate();//设置数据源
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

    public void init() {
        ACache aCache=ACache.get(this);
        userBean= (UserBean) aCache.getAsObject(Constant.USER);
        adapter=new CustomerListAdapter(R.layout.customer_item,R.layout.hca_reading_header,null);
        vwCustomer.setLayoutManager(layoutManager);
        vwCustomer.setAdapter(adapter);
        vwCustomer.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                CustomerSectionEntity<CustomerBean> c=(CustomerSectionEntity)adapter.getItem(position);
                if (!c.isHeader) {
                    Bundle bundle = new Bundle();
                    CustomerBean customerBea=(CustomerBean)c.customerBean;
                    bundle.putInt(Constant.Customer_ID, customerBea.getID());
                    bundle.putString(Constant.Customer_Name, customerBea.getName());
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
        dismissLoading();
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showCustomers(List<Customer> customerBeen) {
        customerBeenList=getCustomerSectionEntitys(customerBeen);
        adapter.addData(customerBeenList);
        //热力公司不加载侧导航
        if (!userBean.getRoleName().equals("热力公司")) {
            initSideBar();
        }
    }

    @Override
    public void showHeatStations(List<HeatStation> heatStations) {

    }

    @Override
    public void setCustomerBeans(List<CustomerBean> customerBeen) {

    }

    private List<CustomerSectionEntity<CustomerBean>> getCustomerSectionEntitys(List<Customer> customerBeen){
        List<CustomerSectionEntity<CustomerBean>> customerSectionEntities=new ArrayList<>();
        mDatas=new ArrayList<>();
        CityBean cityBean;
        CustomerSectionEntity<CustomerBean> customerSectionEntity;
        String city="";
        if (customerBeen.size()>0){
            for (Customer c :customerBeen) {
                city=c.getAddress().split(" ")[1];
                customerSectionEntity=new CustomerSectionEntity<CustomerBean>(true,c.getAddress(),city);
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

    private List<CustomerSectionEntity<CustomerBean>> sortCustomer(List<CustomerSectionEntity<CustomerBean>> customerSectionEntities) {
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
