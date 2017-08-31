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
import com.minoapp.adapter.HeatStationsAdapter;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.common.utils.ACache;
import com.minoapp.data.bean.Customer;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.CustomerSectionEntity;
import com.minoapp.data.bean.HeatStation;
import com.minoapp.data.bean.HeatStationBean;
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
    private CustomerPresenter presenter;
    private ProgressDialog progressDialog;
    HeatStationsAdapter adapter;
    LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
    List<CustomerSectionEntity<HeatStationBean>> customerBeenList;
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
        adapter=new HeatStationsAdapter(R.layout.customer_item,R.layout.hca_reading_header,null);
        recyHeatstations.setLayoutManager(layoutManager);
        recyHeatstations.setAdapter(adapter);
        recyHeatstations.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                CustomerSectionEntity<HeatStationBean> c=(CustomerSectionEntity)adapter.getItem(position);
                if (!c.isHeader) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.HEATSTATION_ID, c.customerBean.getID());
                    bundle.putString(Constant.HEATSTATION_NAME, c.customerBean.getName());
                    bundle.putString(Constant.HEATSTATION_METER_COUNT,c.customerBean.getMeterCount());
                    openActivity(HeatStationMetersActivity.class, bundle);
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

    }

    @Override
    public void showHeatStations(List<HeatStation> heatStations) {
        customerBeenList=getCustomerSectionEntitys(heatStations);
        adapter.addData(customerBeenList);
    }

    @Override
    public void setCustomerBeans(List<CustomerBean> customerBeen) {

    }
    private List<CustomerSectionEntity<HeatStationBean>> getCustomerSectionEntitys(List<HeatStation> customerBeen){
        List<CustomerSectionEntity<HeatStationBean>> customerSectionEntities=new ArrayList<>();
        mDatas=new ArrayList<>();
        CityBean cityBean;
        CustomerSectionEntity<HeatStationBean> customerSectionEntity;
        String city="";
        if (customerBeen.size()>0){
            for (HeatStation h :customerBeen) {
                city=h.getAddress().split(" ")[1];
                customerSectionEntity=new CustomerSectionEntity(true,h.getAddress(),city);
                cityBean=new CityBean(h.getAddress().split(" ")[1]);
                mDatas.add(cityBean);
                customerSectionEntities.add(customerSectionEntity);
                if (h.getData().size()>0){
                    for (HeatStationBean cb :h.getData()) {
                        customerSectionEntity=new CustomerSectionEntity(cb,city);
                        customerSectionEntities.add(customerSectionEntity);
                        cityBean=new CityBean(h.getAddress().split(" ")[1]);
                        mDatas.add(cityBean);
                    }
                }
            }
        }
        return sortCustomer(customerSectionEntities);

    }

    private List<CustomerSectionEntity<HeatStationBean>> sortCustomer(List<CustomerSectionEntity<HeatStationBean>> customerSectionEntities) {
        for (CustomerSectionEntity<HeatStationBean> cu:customerSectionEntities) {
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
