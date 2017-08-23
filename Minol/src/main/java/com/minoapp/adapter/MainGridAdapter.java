package com.minoapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.minoapp.R;
import com.minoapp.data.bean.MenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页GridView适配器
 * Created by Devin on 2017/6/16.
 */

public class MainGridAdapter extends BaseAdapter {
    List<MenuBean> datas=new ArrayList<>();
    private Context mContext;

    public MainGridAdapter(List<MenuBean> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
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
            convertView= View.inflate(mContext, R.layout.main_gridview_item,null);
            holder.tv_title= (TextView) convertView.findViewById(R.id.main_gridview_item_tv);
            holder.iv_icon = (ImageView) convertView.findViewById(R.id.main_gridview_item_iv);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(datas.get(position).getText());
        holder.iv_icon.setImageResource(datas.get(position).getIconID());
        return convertView;

//        View inflate = LayoutInflater.from(mContext).inflate(R.layout.main_gridview_item, null);
//
//        ImageView imageView = (ImageView) inflate.findViewById(R.id.main_gridview_item_iv);
//        imageView.setImageResource(datas.get(position).getIconID());
//        TextView tv = (TextView) inflate.findViewById(R.id.main_gridview_item_tv);
//        tv.setText(datas.get(position).getText());
//        return inflate;
    }
    static class ViewHolder{
        private TextView tv_title;
        private ImageView iv_icon;
    }
}

