package com.minoapp.ui.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.minoapp.R;
import com.minoapp.adapter.HCAReadingAdapter;
import com.minoapp.base.BaseFragment;
import com.minoapp.data.bean.BuildMeterReadingBean;
import com.minoapp.data.bean.HCAReading;
import com.minoapp.data.bean.HCAReadingSectionEntity;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.bean.ReadingBean;
import com.minoapp.data.model.ReadingModel;
import com.minoapp.presenter.ReadingPresenter;
import com.minoapp.presenter.contract.ReadingContract;
import com.minoapp.ui.activity.ResidentDetailActivity;
import com.minoapp.ui.widget.CustomDatePicker;
import com.victor.loading.rotate.RotateLoading;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingFragment extends BaseFragment implements ReadingContract.ReadingView, BaseQuickAdapter.RequestLoadMoreListener, View.OnClickListener {


    @BindView(R.id.recyc_reading)
    RecyclerView recycReading;
    @BindView(R.id.tv_hca_reading_search)
    TextView tvHcaReadingSearch;
    @BindView(R.id.tv_hca_reading_startdate)
    TextView tvHcaReadingStartdate;
    @BindView(R.id.tv_hca_reading_enddate)
    TextView tvHcaReadingEnddate;

    ReadingPresenter presenter;
    int pageIndex = 1;
    int pageSize = 20;
    int localityID = 0;
    HCAReadingAdapter adapter;
    String lastDate = "";
    String startDate = "";
    String endDate = "";
    @BindView(R.id.rotateloading)
    RotateLoading rotateloading;
    private CustomDatePicker customSDatePicker, customEDatePicker;
    ProgressDialog progressDialog;

    public ReadingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        initDatePicker();
        List<HCAReadingSectionEntity> hcaReadingSectionEntities = new ArrayList<>();
        init(hcaReadingSectionEntities);

        ReadingModel readingModel = new ReadingModel();
        presenter = new ReadingPresenter(readingModel, this);
        //暂时写死
        presenter.getHCAReadings(localityID, startDate, endDate, pageIndex, pageSize);
    }

    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        Calendar calendar = Calendar.getInstance();
        tvHcaReadingEnddate.setText(sdf.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, -1);
        String now = sdf2.format(new Date());
        tvHcaReadingStartdate.setText(sdf.format(calendar.getTime()));


        customSDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvHcaReadingStartdate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customSDatePicker.showSpecificTime(false); // 不显示时和分
        customSDatePicker.setIsLoop(false); // 不允许循环滚动

        customEDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.ResultHandler() {
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

    private void init(List<HCAReadingSectionEntity> hcaReadingSectionEntities) {
        startDate = tvHcaReadingStartdate.getText().toString();
        endDate = tvHcaReadingEnddate.getText().toString();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycReading.setLayoutManager(layoutManager);
        adapter = new HCAReadingAdapter(R.layout.hca_reading_item, R.layout.hca_reading_header, hcaReadingSectionEntities);
        adapter.setOnLoadMoreListener(this, recycReading);
        adapter.setEmptyView(R.layout.emptyview, recycReading);
        recycReading.setAdapter(adapter);
        tvHcaReadingSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastDate = "";
                startDate = tvHcaReadingStartdate.getText().toString();
                endDate = tvHcaReadingEnddate.getText().toString();
                adapter.getData().clear();
                adapter.notifyDataSetChanged();
                presenter.getHCAReadings(localityID, startDate, endDate, pageIndex, pageSize);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reading;
    }


    @Override
    public void showHCAReadings(PageBean<HCAReading> pageBean) {
        adapter.addData(getHCAReadingSectionEntityList(pageBean));
        if (pageBean.isHasMore()) {
            pageIndex++;
        }
        adapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void showBReadings(PageBean<BuildMeterReadingBean> pageBean) {

    }

    @Override
    public void showTempReading(PageBean<ReadingBean> pageBean) {

    }


    @Override
    public void onLoadMoreComplete() {
        adapter.loadMoreComplete();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        localityID = ((ResidentDetailActivity) context).setLocalityID();
    }

    private List<HCAReadingSectionEntity> getHCAReadingSectionEntityList(PageBean<HCAReading> pageBean) {
        List<HCAReadingSectionEntity> hcaReadingSectionEntities = new ArrayList<>();
        HCAReadingSectionEntity hcaReadingSectionEntity;
        List<HCAReading> datas = pageBean.getDatas();
        for (HCAReading hcaReading : datas) {
            if (!hcaReading.getDate().equals(lastDate)) {
                hcaReadingSectionEntity = new HCAReadingSectionEntity(true, hcaReading.getDate());
                hcaReadingSectionEntities.add(hcaReadingSectionEntity);
            }
            if (hcaReading.getData().size() > 0) {
                for (ReadingBean readingBean : hcaReading.getData()) {
                    hcaReadingSectionEntity = new HCAReadingSectionEntity(readingBean);
                    hcaReadingSectionEntities.add(hcaReadingSectionEntity);
                }
            }
            lastDate = hcaReading.getDate();
        }
        return hcaReadingSectionEntities;
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.getHCAReadings(localityID, startDate, endDate, pageIndex, pageSize);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_hca_reading_startdate:
                //Toast.makeText(getActivity(), "开始日期", Toast.LENGTH_SHORT).show();
                // 日期格式为yyyy-MM-dd
                customSDatePicker.show(tvHcaReadingStartdate.getText().toString());
                break;

            case R.id.tv_hca_reading_enddate:
                //Toast.makeText(getActivity(), "结束日期", Toast.LENGTH_SHORT).show();
                // 日期格式为yyyy-MM-dd HH:mm
                customEDatePicker.show(tvHcaReadingEnddate.getText().toString());
                break;
        }
    }


    @Override
    public void showLoading() {
        //progressDialog.show();
        rotateloading.start();
    }

    @Override
    public void showError(String msg) {
        dismissLoading();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
//        if (progressDialog.isShowing())
//            progressDialog.dismiss();
        if (rotateloading.isStart())
            rotateloading.stop();
    }


}
