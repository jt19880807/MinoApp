package com.minoapp.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.minoapp.R;
import com.minoapp.adapter.AreaAdapter;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.data.bean.AreaBean;
import com.minoapp.data.model.AreaModel;
import com.minoapp.presenter.AreaPresenter;
import com.minoapp.presenter.contract.AreaContract;

import java.util.List;

import butterknife.BindView;

public class AreaActivity extends BaseActivity implements AreaContract.AreaView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recy_areas)
    RecyclerView recyAreas;

    AreaPresenter presenter;
    AreaAdapter areaAdapter;
    ProgressDialog dialog;
    int customerId=0;
    String customerName="";
    @Override
    protected String getTAG() {
        return AreaActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_area;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView();
        init();
    }

    private void init() {
        dialog=new ProgressDialog(this);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            customerId=bundle.getInt(Constant.Customer_ID);
            customerName=bundle.getString(Constant.Customer_Name);

        }
        toolbar.setTitle(customerName);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        areaAdapter=new AreaAdapter(R.layout.area_item);
        recyAreas.setLayoutManager(layoutManager);
        recyAreas.setAdapter(areaAdapter);
        recyAreas.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AreaBean areaBean=(AreaBean)adapter.getItem(position);
                Bundle bundle=new Bundle();
                bundle.putString(Constant.AREA_NAME,areaBean.getName());
                openActivity(ObjectActivity.class,bundle);
            }
        });
        AreaContract.IAreaModel model=new AreaModel();
        presenter=new AreaPresenter(model,this);
        presenter.getAreasByCustomerId(customerId);

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
    public void showAreas(List<AreaBean> areaBeanList) {
        areaAdapter.addData(areaBeanList);
    }
}