package com.minoapp.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.minoapp.R;
import com.minoapp.ui.widget.CustomYearPicker;
import com.victor.loading.rotate.RotateLoading;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateTestActivity extends AppCompatActivity implements View.OnClickListener {
    int mYear, mMonth, mDay;
    @BindView(R.id.dateDisplay)
    TextView tvBillingDate;
    @BindView(R.id.dateChoose)
    Button dateChoose;
    final int DATE_DIALOG = 1;
    @BindView(R.id.rotateloading)
    RotateLoading rotateloading;
    private CustomYearPicker customYearPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_test);
        ButterKnife.bind(this);
        initDatePicker();
        tvBillingDate.setOnClickListener(this);
        dateChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        rotateloading.start();
    }
    private void initDatePicker() {



        customYearPicker = new CustomYearPicker(this, new CustomYearPicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvBillingDate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", null); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        //customYearPicker.showYear(true); // 不显示时和分
        customYearPicker.setIsLoop(false); // 不允许循环滚动




        //tvHcaReadingEnddate.setOnClickListener(this);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
       // dateDisplay.setText(new StringBuffer().append(mMonth + 1).append("-").append(mDay).append("-").append(mYear).append(" "));
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.dateDisplay){
            customYearPicker.show(tvBillingDate.getText().toString());
        }
    }
}
