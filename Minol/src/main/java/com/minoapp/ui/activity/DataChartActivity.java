package com.minoapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.minoapp.R;
import com.minoapp.common.utils.ChartUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 数据报表展示
 */
public class DataChartActivity extends AppCompatActivity {

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.iv_date)
    ImageView ivDate;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.chart)
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_chart);
        ButterKnife.bind(this);
        ChartUtils.initChart(chart);
        ChartUtils.notifyDataSetChanged(chart,getDatas(),0);
    }

    private List<Entry> getDatas() {
        List<Entry> values=new ArrayList<>();
        values.add(new Entry(0, 18));
        values.add(new Entry(1, 16));
        values.add(new Entry(2, 20));
        values.add(new Entry(3, 19));
        values.add(new Entry(4, 21));
        values.add(new Entry(5, 20));
        values.add(new Entry(6, 23));
        return values;
    }
}
