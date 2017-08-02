package com.minoapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devin on 2017/6/16.
 */

public class MPagerAdapter extends PagerAdapter {
    private List<View> viweList=new ArrayList<>();

    public MPagerAdapter(List<View> viweList) {
        this.viweList = viweList;
    }

    @Override
    public int getCount() {
        return viweList!=null?viweList.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=viweList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viweList.get(position));
    }



}
