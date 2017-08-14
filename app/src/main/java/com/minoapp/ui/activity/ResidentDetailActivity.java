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
import com.minoapp.ui.fragment.HCAListFragment;
import com.minoapp.ui.fragment.HeatMeterFragment;
import com.minoapp.ui.fragment.ReadingFragment;
import com.minoapp.ui.fragment.ResidentsFragment;
import com.minoapp.ui.fragment.TempFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ResidentDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    String roomNum="";
    int localityID=0;
    int isGAIZAO=1;


    @Override
    protected String getTAG() {
        return ResidentDetailActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_residentdetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getIntent().getExtras();
        roomNum=bundle.getString(Constant.LOCALITY_NUMBER);
        localityID=bundle.getInt(Constant.LOCALITY_ID);
        isGAIZAO=bundle.getInt(Constant.ISGAOZAO);
        toolbar.setTitle(roomNum);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        initTablayout();
    }
    private List<FragmentInfo> initFragments() {

        List<FragmentInfo> mFragments = new ArrayList<>(3);
        if (isGAIZAO==1) {
            mFragments.add(new FragmentInfo("热分配计", HCAListFragment.class));
            mFragments.add(new FragmentInfo("读数信息", ReadingFragment.class));
            mFragments.add(new FragmentInfo("账单信息", BillListFragment.class));
        }
        if (isGAIZAO==0){
            mFragments.add(new FragmentInfo("账单信息", BillListFragment.class));

        }
        return mFragments;

    }
    private void initTablayout() {
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

    public int setLocalityID(){
        return  localityID;
    }
}
