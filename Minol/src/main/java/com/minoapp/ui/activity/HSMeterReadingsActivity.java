package com.minoapp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.minoapp.R;
import com.minoapp.adapter.BuildMeterReadingAdapter;
import com.minoapp.adapter.HSMeterReadingsAdapter;
import com.minoapp.adapter.TempReadingAdapter;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.data.bean.HSMeterReadingBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.model.HSReadingModel;
import com.minoapp.presenter.HSReadingsPresenter;
import com.minoapp.presenter.contract.HSReadingsContract;
import com.minoapp.ui.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;

public class HSMeterReadingsActivity extends BaseActivity implements HSReadingsContract.HSReadingsView, View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_hs_reading_search)
    TextView tvHcaReadingSearch;
    @BindView(R.id.tv_hs_reading_startdate)
    TextView tvHcaReadingStartdate;
    @BindView(R.id.tv_hs_reading_enddate)
    TextView tvHcaReadingEnddate;
    @BindView(R.id.recyc_reading)
    RecyclerView recycReading;
    ProgressDialog progressDialog;

    int meterId=0;
    int meterType=0;
    String meterName="";
    HSReadingsPresenter presenter;
    CustomDatePicker customSDatePicker, customEDatePicker;
    String startDate,endDate = "";
    HSMeterReadingsAdapter adapter;
    int pageIndex = 1;
    int pageSize = 10;
    @Override
    protected String getTAG() {
        return HSMeterReadingsActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hsmeter_readings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
        init();
    }

    private void init() {
        progressDialog = new ProgressDialog(this);
        Bundle bundle=getIntent().getExtras();
        meterId=bundle.getInt(Constant.METER_ID);
        meterType=bundle.getInt(Constant.METER_TYPE);
        switch (meterType){
            case 3:
                meterName="热量表";
                break;
            case 9:
                meterName="气候补偿器";
                break;
            case 6:
                meterName="变频器";
                break;
            case 4:
                meterName="电表";
                break;
            case 1:
                meterName="水表";
                break;
        }
        toolbar.setTitle(meterName+"读数");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvHcaReadingSearch.setOnClickListener(this);
        initDatePicker();
        initAdapter();
        HSReadingsContract.IHSReadingModel model=new HSReadingModel();
        presenter=new HSReadingsPresenter(model,this);
        presenter.getHSLastReadings(meterId,meterType);
    }
    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycReading.setLayoutManager(linearLayoutManager);
        adapter = new HSMeterReadingsAdapter(null);
        adapter.setOnLoadMoreListener(this, recycReading);
        adapter.setEmptyView(R.layout.emptyview);
        recycReading.setAdapter(adapter);
    }
    private void initDatePicker() {

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf2.format(new Date());

        customSDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvHcaReadingStartdate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customSDatePicker.showSpecificTime(false); // 不显示时和分
        customSDatePicker.setIsLoop(false); // 不允许循环滚动

        customEDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvHcaReadingEnddate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customEDatePicker.showSpecificTime(false); // 显示时和分
        customEDatePicker.setIsLoop(false); // 允许循环滚动

        tvHcaReadingStartdate.setOnClickListener(this);
        tvHcaReadingEnddate.setOnClickListener(this);
    }
    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void showError(String msg) {
        dismissLoading();
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showReadings(PageBean<HSMeterReadingBean> hsMeterReadingBeen) {
        adapter.addData(hsMeterReadingBeen.getDatas());
        if (hsMeterReadingBeen.isHasMore()) {
            pageIndex++;
        }
        adapter.setEnableLoadMore(hsMeterReadingBeen.isHasMore());
    }

    @Override
    public void showLastReadings(PageBean<HSMeterReadingBean> hsMeterReadingBeen) {
        if (hsMeterReadingBeen.getTotalCount()>0){
            tvHcaReadingEnddate.setText(hsMeterReadingBeen.getDatas().get(0).getDate().split("T")[0]);
            tvHcaReadingStartdate.setText(hsMeterReadingBeen.getDatas().get(0).getDate().split("T")[0]);
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            Calendar calendar = Calendar.getInstance();
            tvHcaReadingEnddate.setText(sdf.format(calendar.getTime()));
            calendar.add(Calendar.MONTH, -1);
            tvHcaReadingStartdate.setText(sdf.format(calendar.getTime()));

        }
        startDate = tvHcaReadingStartdate.getText().toString();
        endDate = tvHcaReadingEnddate.getText().toString();
        adapter.addData(hsMeterReadingBeen.getDatas());
        if (hsMeterReadingBeen.isHasMore()) {
            pageIndex++;
        }
        adapter.setEnableLoadMore(hsMeterReadingBeen.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        adapter.loadMoreComplete();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_hs_reading_startdate:
                customSDatePicker.show(tvHcaReadingStartdate.getText().toString());
                break;
            case R.id.tv_hs_reading_enddate:
                customEDatePicker.show(tvHcaReadingEnddate.getText().toString());
                break;
            case R.id.tv_hs_reading_search:
                searchData();
                break;

        }
    }
    private void searchData() {
        pageIndex = 1;
        startDate = tvHcaReadingStartdate.getText().toString();
        endDate = tvHcaReadingEnddate.getText().toString();
        adapter.getData().clear();
        presenter.getHSReadings(meterId, startDate, endDate, pageIndex, pageSize,meterType);

    }

    @Override
    public void onLoadMoreRequested() {
        presenter.getHSReadings(meterId, startDate, endDate, pageIndex, pageSize,meterType);
    }
}
