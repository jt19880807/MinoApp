package com.minoapp.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.minoapp.R;
import com.minoapp.adapter.BuildMeterReadingAdapter;
import com.minoapp.adapter.TempReadingAdapter;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.data.bean.BuildMeterReadingBean;
import com.minoapp.data.bean.HCAReading;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.bean.ReadingBean;
import com.minoapp.data.model.ReadingModel;
import com.minoapp.presenter.ReadingPresenter;
import com.minoapp.presenter.contract.IReadingModel;
import com.minoapp.presenter.contract.ReadingContract;
import com.minoapp.ui.widget.CustomDatePicker;
import com.victor.loading.rotate.RotateLoading;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;

public class MeterReadingActivity extends BaseActivity implements ReadingContract.ReadingView, View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    String meterType = "";
    BuildMeterReadingAdapter buildMeterReadingAdapter;
    TempReadingAdapter tempReadingAdapter;
    @BindView(R.id.tv_hca_reading_search)
    TextView tvHcaReadingSearch;
    @BindView(R.id.tv_hca_reading_startdate)
    TextView tvHcaReadingStartdate;
    @BindView(R.id.tv_hca_reading_enddate)
    TextView tvHcaReadingEnddate;
    @BindView(R.id.recyc_reading)
    RecyclerView recycReading;

    ProgressDialog progressDialog;
    ReadingPresenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    int pageIndex = 1;
    int pageSize = 10;
    int meterId = 0;
    String startDate,endDate = "";
    CustomDatePicker customSDatePicker, customEDatePicker;
    String meterName = "";
    @BindView(R.id.rotateloading)
    RotateLoading rotateloading;

    @Override
    protected String getTAG() {
        return MeterReadingActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_meter_reading;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            meterType = bundle.getString(Constant.METER_TYPE);
            meterId = bundle.getInt(Constant.METER_ID);
            if (meterType.equals("1")) {
                meterName = "热量表";
            } else if (meterType.equals("3")) {
                meterName = "测温设备";
            }
        }
        toolbar.setTitle(meterName + "读数");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initAdapter();
        initDatePicker();
        tvHcaReadingSearch.setOnClickListener(this);
        IReadingModel model = new ReadingModel();
        presenter = new ReadingPresenter(model, this);
        //searchData();
        if (meterType.equals("1")) {
            presenter.getBuildMeterReadings(meterId,pageIndex, pageSize);
        } else if (meterType.equals("3")) {
            presenter.getTempReadings(meterId,pageIndex, pageSize);
        }
    }

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycReading.setLayoutManager(linearLayoutManager);
        buildMeterReadingAdapter = new BuildMeterReadingAdapter(R.layout.buildmeter_reading_item);
        buildMeterReadingAdapter.setOnLoadMoreListener(this, recycReading);
        buildMeterReadingAdapter.setEmptyView(R.layout.emptyview);
        tempReadingAdapter = new TempReadingAdapter(R.layout.temp_reading_item);
        tempReadingAdapter.setOnLoadMoreListener(this, recycReading);
        tempReadingAdapter.setEmptyView(R.layout.emptyview);


        if (meterType.equals("1")) {
            recycReading.setAdapter(buildMeterReadingAdapter);
        } else if (meterType.equals("3")) {
            recycReading.setAdapter(tempReadingAdapter);
        }


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
        //rotateloading.start();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
//        if(rotateloading.isStart())
//            rotateloading.stop();
    }

    @Override
    public void showHCAReadings(PageBean<HCAReading> pageBean) {

    }

    @Override
    public void showHCALastReadings(PageBean<HCAReading> pageBean) {

    }

    @Override
    public void showBReadings(PageBean<BuildMeterReadingBean> pageBean) {
        buildMeterReadingAdapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore()) {
            pageIndex++;
        }
        buildMeterReadingAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void showBLastReadings(PageBean<BuildMeterReadingBean> pageBean) {
        if (pageBean.getTotalCount()>0){
            tvHcaReadingEnddate.setText(pageBean.getDatas().get(0).getDate().split("T")[0]);
            tvHcaReadingStartdate.setText(pageBean.getDatas().get(0).getDate().split("T")[0]);
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
        buildMeterReadingAdapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore()) {
            pageIndex++;
        }
        buildMeterReadingAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void showTempReading(PageBean<ReadingBean> pageBean) {
        tempReadingAdapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore()) {
            pageIndex++;
        }
        tempReadingAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void showTempLastReading(PageBean<ReadingBean> pageBean) {
        if (pageBean.getTotalCount()>0){
            tvHcaReadingEnddate.setText(pageBean.getDatas().get(0).getDate().split("T")[0]);
            tvHcaReadingStartdate.setText(pageBean.getDatas().get(0).getDate().split("T")[0]);
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            Calendar calendar = Calendar.getInstance();
            tvHcaReadingEnddate.setText(sdf.format(calendar.getTime()));
            calendar.add(Calendar.MONTH, -1);
            tvHcaReadingStartdate.setText(sdf.format(calendar.getTime()));

        }
        startDate = tvHcaReadingStartdate.getText().toString();
        endDate = tvHcaReadingEnddate.getText().toString();
        tempReadingAdapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore()) {
            pageIndex++;
        }
        tempReadingAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        buildMeterReadingAdapter.loadMoreComplete();
        tempReadingAdapter.loadMoreComplete();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_hca_reading_startdate:
                customSDatePicker.show(tvHcaReadingStartdate.getText().toString());
                break;
            case R.id.tv_hca_reading_enddate:
                customEDatePicker.show(tvHcaReadingEnddate.getText().toString());
                break;
            case R.id.tv_hca_reading_search:
                searchData();
                break;

        }
    }

    private void searchData() {
        pageIndex = 1;
        startDate = tvHcaReadingStartdate.getText().toString();
        endDate = tvHcaReadingEnddate.getText().toString();


        if (meterType.equals("1")) {
            buildMeterReadingAdapter.getData().clear();
           // buildMeterReadingAdapter.notifyDataSetChanged();
            presenter.getBuildMeterReadings(meterId, startDate, endDate, pageIndex, pageSize);
        } else if (meterType.equals("3")) {
            tempReadingAdapter.getData().clear();
           // tempReadingAdapter.notifyDataSetChanged();
            presenter.getTempReadings(meterId, startDate, endDate, pageIndex, pageSize);
        }

    }

    @Override
    public void onLoadMoreRequested() {
        if (meterType.equals("1")) {
            presenter.getBuildMeterReadings(meterId, startDate, endDate, pageIndex, pageSize);
        } else if (meterType.equals("3")) {
            presenter.getTempReadings(meterId, startDate, endDate, pageIndex, pageSize);
        }
    }
}
