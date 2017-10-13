package com.minoapp.ui.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.minoapp.R;
import com.minoapp.adapter.ResidentsGridAdapter;
import com.minoapp.common.Constant;
import com.minoapp.common.utils.ACache;
import com.minoapp.common.utils.AnimationUtils;
import com.minoapp.common.utils.ChartUtils;
import com.minoapp.common.utils.LineChartUtils;
import com.minoapp.common.utils.ShowUtils;
import com.minoapp.data.bean.ChartDataBean;
import com.minoapp.data.bean.HCABean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.data.bean.MeterBean;
import com.minoapp.data.model.ChartDataModel;
import com.minoapp.data.model.MeterModel;
import com.minoapp.presenter.ChartDataPresenter;
import com.minoapp.presenter.MeterPresenter;
import com.minoapp.presenter.contract.ChartDataContract;
import com.minoapp.presenter.contract.IMeterModel;
import com.minoapp.presenter.contract.MeterContract;
import com.minoapp.ui.widget.CustomDatePicker;
import com.minoapp.ui.widget.CustomYearMonthPicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * 数据报表展示
 */
public class DataChartActivity extends AppCompatActivity implements ChartDataContract.ChartDataView,
        MeterContract.HeatMeterView,
        View.OnClickListener {

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.iv_date)
    ImageView ivDate;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.hellochart)
    LineChartView lineChart;
    @BindView(R.id.select_true)
    CheckBox selectTrue;
    @BindView(R.id.gridview_residents)
    GridView gridviewResidents;
    @BindView(R.id.tv_search)
    TextView tvSearch;

    @BindView(R.id.tv_hour_date)
    TextView tvHourDate;
    @BindView(R.id.tv_hour_date_pre)
    TextView tvHourDatePre;
    @BindView(R.id.tv_hour_date_next)
    TextView tvHourDateNext;

    @BindView(R.id.tv_day_date)
    TextView tvDayDate;
    @BindView(R.id.tv_day_date_pre)
    TextView tvDayDatePre;
    @BindView(R.id.tv_day_date_next)
    TextView tvDayDateNext;


    ChartDataPresenter chartPresenter;
    MeterPresenter meterPresenter;
    ProgressDialog dialog;
    LineChartData lineChartData = new LineChartData();
    String[] datePoints = {"00:00", "", "", "", "04:00", "", "", "", "08:00", "", "", "", "12:00", "", "", "", "16:00", "", "", "", "20:00", "", "", ""};//X轴的标注
    ResidentsGridAdapter residentsAdapter;
    List<MeterBean> meterBeens = new ArrayList<>();
    List<MeterBean> selectMeter;
    List<Integer> selectIndexs = new ArrayList<>();
    List<AxisValue> mAxisXValues = new ArrayList<>();
    int objectId = 0;
    int meterId = 0;
    static final String[] dates = new String[]{"按时", "按日", "按月"};
    List<String> dateList = Arrays.asList(dates);
    CustomDatePicker customHDatePicker;
    CustomYearMonthPicker customYearMonthPicker;
    String endDate="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_chart);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        objectId = bundle.getInt(Constant.OBJECT_ID);
        meterId = bundle.getInt(Constant.METER_ID);
        endDate=bundle.getString(Constant.ENDATE);
        tvHourDate.setText(endDate);
        dialog = new ProgressDialog(this);
        setOnClickListeners();
        ChartDataContract.IchartDataModel cmodel = new ChartDataModel();
        chartPresenter = new ChartDataPresenter(cmodel, this);
        IMeterModel mModel = new MeterModel();
        meterPresenter = new MeterPresenter(mModel, this);

        initDate();
        initMeters();

        residentsAdapter = new ResidentsGridAdapter(meterBeens, selectIndexs, DataChartActivity.this);
        gridviewResidents.setAdapter(residentsAdapter);
        gridviewResidents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResidentsGridAdapter.ViewHolder viewHolder = (ResidentsGridAdapter.ViewHolder) view.getTag();
                viewHolder.cb_resident.toggle();
                ResidentsGridAdapter.getIsSelected().put(position, viewHolder.cb_resident.isChecked());
                if (viewHolder.cb_resident.isChecked()) {
                    viewHolder.tv_title.setTextColor(ChartUtils.COLORS[position]);
                } else {
                    viewHolder.tv_title.setTextColor(Color.parseColor("#ffffff"));
                }
                selectMeter = new ArrayList<MeterBean>();
                for (int i = 0; i < ResidentsGridAdapter.getIsSelected().size(); i++) {
                    if (ResidentsGridAdapter.getIsSelected().get(i)) {
                        selectMeter.add(meterBeens.get(i));
                    }
                }
                if (selectMeter.size() == meterBeens.size()) {
                    selectTrue.setChecked(true);
                } else {
                    selectTrue.setChecked(false);
                }

            }
        });
        //全选或者全不选
        selectTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMeter = new ArrayList<MeterBean>();
                if (selectTrue.isChecked()) {
                    for (int i = 0; i < meterBeens.size(); i++) {
                        ResidentsGridAdapter.getIsSelected().put(i, selectTrue.isChecked());
                        selectMeter.add(meterBeens.get(i));
                    }
                } else {
                    for (int i = 0; i < meterBeens.size(); i++) {
                        ResidentsGridAdapter.getIsSelected().put(i, selectTrue.isChecked());
                    }
                }

                residentsAdapter.notifyDataSetChanged();
            }
        });

        Log.d("ttdate",tvHourDate.getText().toString());
        chartPresenter.getChartData(getSelectMeterIds(), tvHourDate.getText().toString(), tvHourDate.getText().toString());

        getAxisXLables();//获取x轴的标注
        LineChartUtils.initLineChart(lineChart, lineChartData, mAxisXValues);
    }

    private void setOnClickListeners() {
        tvSearch.setOnClickListener(this);
        ivDate.setOnClickListener(this);
        tvDate.setOnClickListener(this);
        tvHourDate.setOnClickListener(this);
        tvHourDatePre.setOnClickListener(this);
        tvHourDateNext.setOnClickListener(this);
        tvDayDate.setOnClickListener(this);
        tvDayDatePre.setOnClickListener(this);
        tvDayDateNext.setOnClickListener(this);
    }

    //初始化日期控件
    private void initDate() {
        // 1、按小时初始化
        initHourDate();
        // 2、按日初始化
        //initYearMonthDate();
        // 3、按月初始化

    }

    // 按小时初始化
    private void initHourDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        customHDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvHourDate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customHDatePicker.showSpecificTime(false); // 不显示时和分
        customHDatePicker.setIsLoop(false); // 不允许循环滚动
    }

    private void initYearMonthDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String now = sdf.format(new Date());
        customYearMonthPicker = new CustomYearMonthPicker(this, new CustomYearMonthPicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                Log.d("abc","time:"+time);
                tvDayDate.setText(time);
            }
        }, "2010-01-01", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customYearMonthPicker.showSpecificTime(false); // 不显示时和分
        customYearMonthPicker.setIsLoop(false); // 不允许循环滚动
    }

    private String getSelectMeterIds() {
        StringBuilder sb = new StringBuilder();
        for (MeterBean meterBean : selectMeter) {
            sb.append(meterBean.getID() + ",");
        }
        return sb.toString();
    }

    private void initMeters() {
        ACache aCache = ACache.get(this);
        meterBeens = (ArrayList<MeterBean>) aCache.getAsObject("temps-" + objectId);
        int index = getIndex(meterId);
        selectIndexs.add(index);
        selectMeter = new ArrayList<>();
        selectMeter.add(meterBeens.get(index));
    }

    private int getIndex(int meterId) {
        int index = 0;
        for (int i = 0; i < meterBeens.size(); i++) {
            if (meterBeens.get(i).getID() == meterId) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables() {
        for (int i = 0; i < datePoints.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(datePoints[i]));
        }
    }


    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void showError(String msg) {
        dismissLoading();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        if (dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public void showData(List<ChartDataBean> chartDataBeen) {
        LineChartUtils.setLinesDatas(lineChart, lineChartData, getLines(chartDataBeen));


    }

    private List<Line> getLines(List<ChartDataBean> chartDataBeens) {
        List<Line> lines = new ArrayList<>();
        ChartDataBean mChartDataBean;
        int mHour = 0;
        PointValue pointValue;
        for (int i = 0; i < chartDataBeens.size(); i++) {
            mChartDataBean = chartDataBeens.get(i);
            List<PointValue> values = new ArrayList<>();
            for (int j = 0; j < mChartDataBean.getData().size(); j++) {
                mHour = Integer.parseInt(mChartDataBean.getData().get(j).getDate().split("T")[1].split(":")[0]);
                pointValue = new PointValue(mHour, (float) mChartDataBean.getData().get(j).getValue());
                pointValue.setLabel(mChartDataBean.getData().get(j).getValue() + "");
                values.add(pointValue);
            }
            Line line = new Line(values);
            line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
            line.setCubic(false);//曲线是否平滑，即是曲线还是折线
            line.setFilled(false);//是否填充曲线的面积
            line.setHasLabelsOnlyForSelected(true);
            line.setColor(ChartUtils.COLORS[getIndex(mChartDataBean.getID())]);
            line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
            line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
            lines.add(line);
        }
        return lines;
    }

    @Override
    public void showHeatMeter(List<HeatMeterBean> beanList) {

    }

    @Override
    public void showTemp(List<MeterBean> beanList) {

    }

    @Override
    public void showHCA(List<HCABean> beanList) {

    }

    @Override
    public void onLoadMoreComplete() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                Log.d("ttdate",tvHourDate.getText().toString());
                chartPresenter.getChartData(getSelectMeterIds(), tvHourDate.getText().toString(), tvHourDate.getText().toString());
                break;
            case R.id.tv_date:
            case R.id.iv_date:
                String data = tvDate.getText().toString();
                if (!ShowUtils.isPopupWindowShowing()) {
                    AnimationUtils.startModeSelectAnimation(ivDate, true);
                    ShowUtils.showPopupWindow(this, tvDate, 90, 166, dateList,
                            new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    ShowUtils.updatePopupWindow(position);
                                    AnimationUtils.startModeSelectAnimation(ivDate, false);
                                    ShowUtils.popupWindowDismiss();
                                    tvDate.setText(dateList.get(position));
                                    if (position==1){
                                        initYearMonthDate();
                                        tvHourDate.setVisibility(View.GONE);
                                        tvHourDatePre.setVisibility(View.GONE);
                                        tvHourDateNext.setVisibility(View.GONE);
                                        tvDayDate.setVisibility(View.VISIBLE);
                                        tvDayDatePre.setVisibility(View.VISIBLE);
                                        tvDayDateNext.setVisibility(View.VISIBLE);
                                        tvDayDate.setText(endDate.split("-")[0]+"-"+endDate.split("-")[1]);
                                    }

                                }
                            });
                } else {
                    AnimationUtils.startModeSelectAnimation(ivDate, false);
                    ShowUtils.popupWindowDismiss();
                }

                if (dateList.get(0).equals(data)) {
                    ShowUtils.updatePopupWindow(0);
                } else if (dateList.get(1).equals(data)) {
                    ShowUtils.updatePopupWindow(1);
                } else if (dateList.get(2).equals(data)) {
                    ShowUtils.updatePopupWindow(2);
                }
                break;
            case R.id.tv_hour_date:
                customHDatePicker.show(tvHourDate.getText().toString());
                break;
            case R.id.tv_day_date:
                customYearMonthPicker.show(tvDayDate.getText().toString());
                break;
            case R.id.tv_hour_date_pre://前一天
                setDay(-1);
                break;
            case R.id.tv_hour_date_next://后一天
                setDay(1);
                break;
            case R.id.tv_day_date_pre://前一天
                setMonth(-1);
                break;
            case R.id.tv_day_date_next://后一天
                setMonth(1);
                break;
        }
    }

    private void setDay(int days){
        String date=tvHourDate.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            Date dd= sdf.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(dd);
            c.add(Calendar.DAY_OF_MONTH, days);
            tvHourDate.setText(sdf.format(c.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        chartPresenter.getChartData(getSelectMeterIds(), tvHourDate.getText().toString(), tvHourDate.getText().toString());
    }
    private void setMonth(int month){
        String date=tvDayDate.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
        try {
            Date dd= sdf.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(dd);
            c.add(Calendar.MONTH, month);
            tvDayDate.setText(sdf.format(c.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //chartPresenter.getChartData(getSelectMeterIds(), tvHourDate.getText().toString(), tvHourDate.getText().toString());
    }
}


