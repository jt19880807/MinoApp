package com.minoapp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.minoapp.R;
import com.minoapp.adapter.ViewPagerAdapter;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.data.bean.FragmentInfo;
import com.minoapp.ui.fragment.BillListFragment;
import com.minoapp.ui.fragment.HeatMeterFragment;
import com.minoapp.ui.fragment.IncidentFragment;
import com.minoapp.ui.fragment.ResidentsFragment;
import com.minoapp.ui.fragment.TempFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ObjectDetaileActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    String objectName ="";
    int objectId=0;
    int incidentCount=0;
    @Override
    protected String getTAG() {
        return ObjectDetaileActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_object_detaile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null) {
            objectName = bundle.getString(Constant.OBJECT_NAME);
            incidentCount=bundle.getInt(Constant.INCIDENT_COUNT);
            objectId=bundle.getInt(Constant.OBJECT_ID);
            toolbar.setTitle(objectName);
        }
        toolbar.setTitle(objectName);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //setSupportActionBar(toolbar);
        initTablayout();
    }

    private List<FragmentInfo> initFragments() {

        List<FragmentInfo> mFragments = new ArrayList<>(3);

        mFragments.add(new FragmentInfo("住户", ResidentsFragment.class));
        mFragments.add(new FragmentInfo("热量表", HeatMeterFragment.class));
        if (!userBean.getRoleName().equals("住户")||(userBean.getRoleName().equals("住户")&&userBean.getTempCount()>0)) {
            mFragments.add(new FragmentInfo("测温设备", TempFragment.class));
        }
        mFragments.add(new FragmentInfo("账单信息", BillListFragment.class));
        if (incidentCount>0){
            mFragments.add(new FragmentInfo("报警信息", IncidentFragment.class));
        }
        return mFragments;

    }

    public void initTablayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), initFragments());
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


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

    public int setObjectId(){
        return objectId;
    }
}
