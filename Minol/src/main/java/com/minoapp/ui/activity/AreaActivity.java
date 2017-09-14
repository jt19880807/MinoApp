package com.minoapp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.minoapp.R;
import com.minoapp.adapter.AreaAdapter;
import com.minoapp.adapter.AreaExpandableAdapter;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.data.bean.AreaBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.model.AreaModel;
import com.minoapp.presenter.AreaPresenter;
import com.minoapp.presenter.contract.AreaContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AreaActivity extends BaseActivity implements AreaContract.AreaView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
//    @BindView(R.id.recy_areas)
//    RecyclerView recyAreas;
    @BindView(R.id.recy_areas)
    ExpandableListView recyAreas;

    AreaPresenter presenter;
    AreaExpandableAdapter areaAdapter;
    ProgressDialog dialog;
    int customerId=0;
    String customerName="";
    List<AreaBean> areas=new ArrayList<>();
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
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.action_search){
                    Bundle bundle=new Bundle();
                    bundle.putString(Constant.SEARCH_TYPE,"Area");
                    bundle.putInt(Constant.Customer_ID,customerId);
                    openActivity(SearchActivity.class,bundle);
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
        recyAreas.setGroupIndicator(null);
        recyAreas.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                AreaBean areaBean=areas.get(groupPosition);
                Bundle bundle=new Bundle();
                bundle.putString(Constant.AREA_NAME,areaBean.getName());
                openActivity(ObjectActivity.class,bundle);
                return false;
            }
        });


        AreaContract.IAreaModel model=new AreaModel();
        presenter=new AreaPresenter(model,this);
        presenter.getAreasByCustomerId(customerId);

    }

    private void initView(List<AreaBean> areaBeanList){
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        areaAdapter=new AreaExpandableAdapter(areaBeanList,AreaActivity.this);
        //recyAreas.setLayoutManager(layoutManager);
        recyAreas.setAdapter(areaAdapter);
//        recyAreas.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                AreaBean areaBean=(AreaBean)adapter.getItem(position);
//                Bundle bundle=new Bundle();
//                bundle.putString(Constant.AREA_NAME,areaBean.getName());
//                openActivity(ObjectActivity.class,bundle);
//            }
//        });

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
        areas=areaBeanList;
        initView(areaBeanList);
    }


//    @Override
//    public void showAreas(PageBean<AreaBean> areaBeanList) {
//        areaAdapter.addData(areaBeanList.getDatas());
//        if (areaBeanList.isHasMore())
//            pageIndex++;
//        areaAdapter.setEnableLoadMore(areaBeanList.isHasMore());
    //}

}
