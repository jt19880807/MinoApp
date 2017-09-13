package com.minoapp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.minoapp.R;
import com.minoapp.adapter.SearchAdapter;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.common.utils.ACache;
import com.minoapp.data.bean.AreaBean;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.HeatStationBean;
import com.minoapp.data.bean.UserBean;
import com.minoapp.data.model.SearchModel;
import com.minoapp.presenter.SearchPresenter;
import com.minoapp.presenter.contract.SearchContact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements SearchContact.SearchView {

    @BindView(R.id.searchTextView)
    EditText searchTextView;
    @BindView(R.id.action_clear_btn)
    ImageView actionClearBtn;
    @BindView(R.id.search_bar)
    RelativeLayout searchBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.search_customer)
    ListView searchCustomer;

    String searchType="";
    int customerId=0;
    UserBean userBean;
    SearchAdapter<CustomerBean> adapter;
    private SearchPresenter presenter;

    @Override
    protected String getTAG() {
        return SearchActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        ACache aCache=ACache.get(this);
        userBean= (UserBean) aCache.getAsObject(Constant.USER);
        Bundle bundle=getIntent().getExtras();
        SearchContact.SearchModel model=new SearchModel();
        presenter=new SearchPresenter(model,this);
        searchType=bundle.getString(Constant.SEARCH_TYPE);
        mToolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(android.R.color.white)
                        )
        );
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0){
                    searchCustomer.setVisibility(View.GONE);

                }
                else {
                    adapter.getFilter().filter(s);
                    searchCustomer.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0){
                    searchCustomer.setVisibility(View.GONE);

                }
                else {
                    adapter.getFilter().filter(s);
                    searchCustomer.setVisibility(View.VISIBLE);
                }
            }
        });
        if (searchType.equals("Customer")){
            searchTextView.setHint("热力公司");
            presenter.getCustomerBeans(userBean.getID());
        }
        else if (searchType.equals("Area")){
            searchTextView.setHint("小区名称");
            customerId=bundle.getInt(Constant.Customer_ID);
            presenter.getAreaBeans(customerId);
        }
        else if (searchType.equals("HeatStation")){
            searchTextView.setHint("换热站名称");
            presenter.getHeatStationBeans(userBean.getID());
        }
    }


    private void initCustomerView(List<CustomerBean> customers) {
        adapter=new SearchAdapter(SearchActivity.this,customers);
        searchCustomer.setAdapter(adapter);
        searchCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerBean c=(CustomerBean) searchCustomer.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.Customer_ID, c.getID());
                bundle.putString(Constant.Customer_Name, c.getName());
                openActivity(AreaActivity.class, bundle);
            }
        });

    }
    private void initAreaView(List<AreaBean> areaBeen) {
        adapter=new SearchAdapter(SearchActivity.this,areaBeen);
        searchCustomer.setAdapter(adapter);
        searchCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AreaBean areaBean=(AreaBean) searchCustomer.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putString(Constant.AREA_NAME,areaBean.getName());
                openActivity(ObjectActivity.class,bundle);
            }
        });

    }
    private void initHeatStationView(List<HeatStationBean> heatStationBeen) {
        adapter=new SearchAdapter(SearchActivity.this,heatStationBeen);
        searchCustomer.setAdapter(adapter);
        searchCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HeatStationBean c=(HeatStationBean) searchCustomer.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.HEATSTATION_ID, c.getID());
                bundle.putString(Constant.HEATSTATION_NAME, c.getName());
                bundle.putString(Constant.HEATSTATION_METER_COUNT,c.getMeterCount());
                openActivity(HeatStationMetersActivity.class, bundle);
            }
        });

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showCustomers(List<CustomerBean> customerBeen) {
        initCustomerView(customerBeen);
    }

    @Override
    public void showAreas(List<AreaBean> areaBeen) {
        initAreaView(areaBeen);
    }

    @Override
    public void showHeatStations(List<HeatStationBean> heatStationBeen) {
        initHeatStationView(heatStationBeen);
    }
}
