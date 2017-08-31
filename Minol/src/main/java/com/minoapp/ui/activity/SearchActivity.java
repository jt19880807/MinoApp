package com.minoapp.ui.activity;

import android.app.Activity;
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
import android.widget.Toast;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.minoapp.R;
import com.minoapp.adapter.SearchCustomerAdapter;
import com.minoapp.data.bean.Customer;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.HeatStation;
import com.minoapp.data.model.CustomerModel;
import com.minoapp.presenter.CustomerPresenter;
import com.minoapp.presenter.contract.CustomerContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends Activity implements CustomerContract.CustomerView {

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
    SearchCustomerAdapter adapter;
    private CustomerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        CustomerContract.ICustomerModel model=new CustomerModel();
        presenter=new CustomerPresenter(model,this);
        presenter.getAllCustomerBeans("1");
        //initData();
        //initView();

    }



    private void initView(List<CustomerBean> customers) {
        adapter=new SearchCustomerAdapter(SearchActivity.this,customers);
        searchCustomer.setAdapter(adapter);
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
                // Toast.makeText(SearchActivity.this, s, Toast.LENGTH_SHORT).show();
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

        searchCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerBean c=(CustomerBean) searchCustomer.getItemAtPosition(position);
                Toast.makeText(SearchActivity.this, c.getID()+"|"+c.getName(), Toast.LENGTH_SHORT).show();
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
    public void showCustomers(List<Customer> customerBeen) {

    }

    @Override
    public void showHeatStations(List<HeatStation> heatStations) {

    }

    @Override
    public void setCustomerBeans(List<CustomerBean> customerBeen) {
        initView(customerBeen);
    }
}
