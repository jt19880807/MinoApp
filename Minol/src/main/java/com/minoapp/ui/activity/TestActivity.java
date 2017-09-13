package com.minoapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.minoapp.R;
import com.minoapp.adapter.AreaExpandableAdapter;
import com.minoapp.data.bean.AreaBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.main_recyclerview)
    ExpandableListView mainRecyclerview;
    AreaExpandableAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        List<AreaBean> areaBeen=getDatas();
        myAdapter = new AreaExpandableAdapter(areaBeen,TestActivity.this);
        mainRecyclerview.setGroupIndicator(null);
        mainRecyclerview.setAdapter(myAdapter);
        mainRecyclerview.setGroupIndicator(null);
    }

    private List<AreaBean> getDatas(){
        List<AreaBean> areaBeen=new ArrayList<>();

        for (int i=0;i<5;i++){
            areaBeen.add(new AreaBean("测试小区"+i,i*10));
        }
        return  areaBeen;
    }
}
