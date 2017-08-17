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

import java.util.List;

import butterknife.BindView;

public class ObjectActivity extends BaseActivity implements ObjectContract.ObjectView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_object)
    RecyclerView recyclerObject;

    private ObjectsAdapter mAdapter;
    private ObjectPresenter presenter;
    private ProgressDialog progressDialog;
//    private int customerID=0;
//    private String customerName="";
    private String area="";
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
//        customerID=bundle.getInt(Constant.Customer_ID);
//        customerName=bundle.getString(Constant.Customer_Name);
        area=bundle.getString(Constant.AREA_NAME);
        toolbar.setTitle(area);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //setSupportActionBar(toolbar);

        progressDialog=new ProgressDialog(this);
        showObjects();

        ObjectModel model=new ObjectModel();

        presenter=new ObjectPresenter(model,this);

        presenter.getObjects(1,area);
    }
    private void showObjects(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        mAdapter =new ObjectsAdapter(R.layout.object_item);
        recyclerObject.setLayoutManager(layoutManager);
        recyclerObject.setAdapter(mAdapter);
        recyclerObject.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=getIntent().getExtras();
                ObjectBean objectBean=(ObjectBean) adapter.getItem(position);
                bundle.putInt(Constant.OBJECT_ID,objectBean.getID());
                bundle.putString(Constant.OBJECT_NAME,objectBean.getName());
                bundle.putInt(Constant.INCIDENT_COUNT,objectBean.getIncedentsCount());

                openActivity(ObjectDetaileActivity.class,bundle);
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
    public void showData(List<ObjectBean> pageBean) {
        mAdapter.addData(pageBean);
//        if (pageBean.isHasMore()) {
//            pageIndex++;
//        }
//        mAdapter.setEnableLoadMore(pageBean.isHasMore());
    }


}
