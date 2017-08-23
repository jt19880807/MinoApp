package com.minoapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.minoapp.R;
import com.minoapp.ui.widget.SideBar.CityBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateTestActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.indexBar)
    com.minoapp.ui.widget.SideBar.SideBar indexBar;
    @BindView(R.id.tvSideBarHint)
    TextView tvSideBarHint;
    List<CityBean> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_test);
        ButterKnife.bind(this);
        initData();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        indexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                //.setNeedRealIndex(false)//设置需要真实的索引
                .setmLayoutManager(layoutManager)//设置RecyclerView的LayoutManager
                .setmSourceDatas(mDatas);//设置数据源
    }
    private  void initData(){
        mDatas=new ArrayList<>();
        CityBean cityBean=new CityBean();
        cityBean.setCity("北京市");
        mDatas.add(cityBean);
        cityBean=new CityBean();
        cityBean.setCity("海淀区");
        mDatas.add(cityBean);
    }

}
