package com.minoapp.common.utils;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.minoapp.ui.activity.DataChartActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Devin on 2017/9/15.
 */

public class LineChartUtils {

    public static int dayValue = 0;
    public static int weekValue = 1;
    public static int monthValue = 2;

    /**
     * 初始化LineChart
     *
     * @param lineChart
     * @param mAxisXValues
     * @return
     */
    public static LineChartView initLineChart(LineChartView lineChart, LineChartData mLineData, List<AxisValue> mAxisXValues) {
        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.WHITE);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        mLineData.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        Axis axisY = new Axis().setHasLines(true);  //Y轴
        axisY.setName("温度");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        axisY.setTextColor(Color.WHITE);
        mLineData.setAxisYLeft(axisY);  //Y轴设置在左边
        lineChart.setLineChartData(mLineData);
        lineChart.setValueSelectionEnabled(true);
        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setVisibility(View.VISIBLE);

        return lineChart;
    }

    public static void setLinesDatas(LineChartView lineChart, LineChartData mLineData, List<Line> lines) {
//        mLineData.notifyAll();
//        mLineData.getLines().add();
        mLineData.setLines(lines);
        lineChart.setLineChartData(mLineData);
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 23;
        v.top = 30;
        v.bottom = 15;
        lineChart.setMaximumViewport(v);
        lineChart.setCurrentViewport(v);
    }


}
