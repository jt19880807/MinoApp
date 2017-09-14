package com.minoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.minoapp.R;
import com.minoapp.data.bean.AreaBean;

import java.util.List;

/**
 * Created by Devin on 2017/9/13.
 */

public class AreaExpandableAdapter extends BaseExpandableListAdapter{
    List<AreaBean> areaBeen;
    private Context mContext;

    public AreaExpandableAdapter(List<AreaBean> areaBeen, Context mContext) {
        this.areaBeen = areaBeen;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return areaBeen.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return areaBeen.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return areaBeen.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        GroupHolder holder = null;
        if(view == null){
            holder = new GroupHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.area_group, null);
            holder.groupName = (TextView)view.findViewById(R.id.tv_area_groupname);
            holder.arrow = (ImageView)view.findViewById(R.id.iv_arrow);
            view.setTag(holder);
        }else{
            holder = (GroupHolder)view.getTag();
        }

        //判断是否已经打开列表
        if(isExpanded){
            holder.arrow.setBackgroundResource(R.mipmap.ic_expand);
        }else{
            holder.arrow.setBackgroundResource(R.mipmap.ic_collapse);
        }

        holder.groupName.setText(areaBeen.get(groupPosition).getName());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ChildHolder holder = null;
        if(view == null){
            holder = new ChildHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.area_item, null);
            holder.buildingcount = (TextView)view.findViewById(R.id.tv_area_buildingcount);
            holder.totaluser = (TextView)view.findViewById(R.id.tv_area_totaluser);
            holder.gzuser = (TextView)view.findViewById(R.id.tv_area_gzuser);
            holder.heatmeter = (TextView)view.findViewById(R.id.tv_area_heatmeter);
            holder.hca = (TextView)view.findViewById(R.id.tv_area_hca);
            holder.temp = (TextView)view.findViewById(R.id.tv_area_temp);
            holder.incidents = (TextView)view.findViewById(R.id.tv_area_incidents);
            view.setTag(holder);
        }else{
            holder = (ChildHolder)view.getTag();
        }

//        if(childPosition == 0){
//            holder.divider.setVisibility(View.GONE);
//        }

        //holder.sound.setBackgroundResource(R.drawable.sound);
        holder.buildingcount.setText("楼栋数量："+areaBeen.get(groupPosition).getBuildingCount());
        holder.totaluser.setText(areaBeen.get(groupPosition).getResidentsCount()+"");
        holder.gzuser.setText(areaBeen.get(groupPosition).getTransResidentsCount()+"");
        holder.heatmeter.setText(areaBeen.get(groupPosition).getHeatMeterCount()+"");
        holder.hca.setText(areaBeen.get(groupPosition).getHCACount()+"");
        holder.temp.setText(areaBeen.get(groupPosition).getTempMeterCount()+"");
        holder.incidents.setText(areaBeen.get(groupPosition).getIncedentsCount()+"");

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder{
        public TextView groupName;
        public ImageView arrow;
    }

    class ChildHolder{
        public TextView buildingcount;
        public TextView totaluser;
        public TextView gzuser;
        public TextView heatmeter;
        public TextView hca;
        public TextView temp;
        public TextView incidents;
    }
}
