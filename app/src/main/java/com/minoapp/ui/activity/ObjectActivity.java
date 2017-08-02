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
import com.minoapp.adapter.ObjectsAdapter;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.data.bean.ObjectBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.model.ObjectModel;
import com.minoapp.presenter.ObjectPresenter;
import com.minoapp.presenter.contract.ObjectContract;

import butterknife.BindView;

public class ObjectActivity extends BaseActivity implements ObjectContract.ObjectView,BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_object)
    RecyclerView recyclerObject;

    private int pageIndex=1;
    private int pageSize=10;
    private ObjectsAdapter mAdapter;
    private ObjectPresenter presenter;
    private ProgressDialog progressDialog;
    private int customerID=0;
    private String customerName="";

    @Override
    protected String getTAG() {
        return ObjectActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_object;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getIntent().getExtras();
        customerID=bundle.getInt(Constant.Customer_ID);
        customerName=bundle.getString(Constant.Customer_Name);
        toolbar.setTitle(customerName);
        setSupportActionBar(toolbar);

        progressDialog=new ProgressDialog(this);
        showObjects();

        ObjectModel model=new ObjectModel();

        presenter=new ObjectPresenter(model,this);

        presenter.getObjects(1,customerID,pageIndex ,pageSize);
    }
    private void showObjects(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        mAdapter =new ObjectsAdapter(R.layout.object_item);
        mAdapter.setOnLoadMoreListener(this);
        recyclerObject.setLayoutManager(layoutManager);
        recyclerObject.setAdapter(mAdapter);
        recyclerObject.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                openActivity(ObjectDetaileActivity.class);
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
    public void showData(PageBean<ObjectBean> pageBean) {
        mAdapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore()) {
            pageIndex++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        Toast.makeText(ObjectActivity.this, "下一页", Toast.LENGTH_SHORT).show();
        //mAdapter.setEnableLoadMore(true);
        presenter.getObjects(1,customerID,pageIndex ,pageSize);
    }
}
