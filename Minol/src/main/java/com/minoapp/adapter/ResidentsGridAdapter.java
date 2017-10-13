package com.minoapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.minoapp.R;
import com.minoapp.common.utils.ChartUtils;
import com.minoapp.data.bean.MenuBean;
import com.minoapp.data.bean.MeterBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 首页GridView适配器
 * Created by Devin on 2017/6/16.
 */

public class ResidentsGridAdapter extends BaseAdapter {
    List<MeterBean> datas=new ArrayList<>();
    private static HashMap<Integer, Boolean> isSelected;
    private Context mContext;

    public ResidentsGridAdapter(List<MeterBean> datas,List<Integer> selectedIndexs, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
        initData(selectedIndexs);
    }

    private void initData(List<Integer> selectedIndexs) {
        isSelected = new HashMap<Integer, Boolean>();
        for (int i=0;i<datas.size();i++){
            if (selectedIndexs.contains(i)){
                getIsSelected().put(i,true);
            }
            else {
                getIsSelected().put(i,false);
            }
        }
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= View.inflate(mContext, R.layout.residents_gridview_item,null);
            holder.tv_title= (TextView) convertView.findViewById(R.id.resident_grid_item_tv);
            holder.cb_resident = (CheckBox) convertView.findViewById(R.id.cb_resident);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(datas.get(position).getLocalityNum());
        holder.cb_resident.setChecked(getIsSelected().get(position));
        if (getIsSelected().get(position)){
            holder.tv_title.setTextColor(ChartUtils.COLORS[position]);

        }
        else {
            holder.tv_title.setTextColor(Color.parseColor("#ffffff"));
        }
        return convertView;

    }
    public static class ViewHolder{
        public TextView tv_title;
        public CheckBox cb_resident;
    }
    public static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
    }
}

