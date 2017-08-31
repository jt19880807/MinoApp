package com.minoapp.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.common.collect.Interner;
import com.minoapp.R;
import com.minoapp.adapter.ViewPagerAdapter;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.data.bean.FragmentInfo;
import com.minoapp.ui.fragment.hsfragment.HSBPQFragment;
import com.minoapp.ui.fragment.hsfragment.HSDBFragment;
import com.minoapp.ui.fragment.hsfragment.HSQHBCQFragment;
import com.minoapp.ui.fragment.hsfragment.HSRLBFragment;
import com.minoapp.ui.fragment.hsfragment.HSSBFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HeatStationMetersActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    ProgressDialog dialog;

    int heatStationId=0;
    String heatStationName="";
    String heatStationMeterCount="";
    @Override
    protected String getTAG() {
        return HeatStationMetersActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_heat_station_meters;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * 加载Fragments
     */
    private List<FragmentInfo> initFragments() {
        List<FragmentInfo> fragmentInfos=new ArrayList<>();
        if (!"".equals(heatStationMeterCount)){
            String[] meterCounts = heatStationMeterCount.split(",");
            if (Integer.parseInt(meterCounts[0])>0){
                fragmentInfos.add(new FragmentInfo("热量表", HSRLBFragment.class));
            }
            if (Integer.parseInt(meterCounts[2])>0){
                fragmentInfos.add(new FragmentInfo("变频器", HSBPQFragment.class));
            }
            if (Integer.parseInt(meterCounts[1])>0){
                fragmentInfos.add(new FragmentInfo("气候补偿器", HSQHBCQFragment.class));
            }
            if (Integer.parseInt(meterCounts[3])>0){
                fragmentInfos.add(new FragmentInfo("电表", HSDBFragment.class));
            }
            if (Integer.parseInt(meterCounts[4])>0){
                fragmentInfos.add(new FragmentInfo("水表", HSSBFragment.class));
            }
        }






        return fragmentInfos;
    }

    public void initTablayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), initFragments());
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    /**
     * 初始化参数
     */
    private void init() {
        dialog=new ProgressDialog(mContext);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            heatStationId=bundle.getInt(Constant.HEATSTATION_ID);
            heatStationName=bundle.getString(Constant.HEATSTATION_NAME);
            heatStationMeterCount=bundle.getString(Constant.HEATSTATION_METER_COUNT);
        }
        toolbar.setTitle(heatStationName);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initTablayout();
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
    public int setHeatStationId(){
        return heatStationId;
    }
}
