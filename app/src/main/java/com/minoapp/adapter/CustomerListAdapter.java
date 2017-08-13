package com.minoapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.minoapp.R;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.CustomerSectionEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Devin on 2017/6/28.
 */

//public class CustomerListAdapter extends RecyclerView.Adapter<ViewHolder> {
//    List<CustomerBean> customerBeen =new ArrayList<>();
//    private Context mcontext;
//    private LayoutInflater inflater;
//    private OnItemClickListener listener;
//    public CustomerListAdapter(List<CustomerBean> customerBeen, Context context) {
//        this.customerBeen = customerBeen;
//        this.mcontext =context;
//        inflater=LayoutInflater.from(mcontext);
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view=inflater.inflate(R.layout.customer_item,parent,false);
//        ViewHolder vh= new ViewHolder(view);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, final int position) {
//        final CustomerBean c= customerBeen.get(position);
//        holder.tv_customername.setText(customerBeen.get(position).getName());
//        holder.tv_customerarea.setText(customerBeen.get(position).getArea());
//        holder.tv_province.setText(customerBeen.get(position).getProvince()+" "+ customerBeen.get(position).getCity());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener!=null){
//                    listener.onItemClick(position,c);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return customerBeen.size();
//    }
//
//
//    public interface OnItemClickListener{
//        void onItemClick(int position, Object item);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener){
//        this.listener=listener;
//
//    }
//}
//
//class ViewHolder extends RecyclerView.ViewHolder{
//    TextView tv_customername;
//    TextView tv_province;
//    TextView tv_customerarea;
//
//    public ViewHolder(View itemView) {
//        super(itemView);
//        tv_customername = (TextView) itemView.findViewById(R.id.tv_customername);
//        tv_customerarea = (TextView) itemView.findViewById(R.id.tv_customerarea);
//        tv_province = (TextView) itemView.findViewById(R.id.tv_province);
//
//    }
//
//}

public class CustomerListAdapter extends BaseSectionQuickAdapter<CustomerSectionEntity,BaseViewHolder>{


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public CustomerListAdapter(int layoutResId, int sectionHeadResId, List<CustomerSectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, CustomerSectionEntity item) {
        helper.setText(R.id.tv_hca_reading_header,item.title);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerSectionEntity item) {
        helper.setText(R.id.tv_customerarea,item.customerBean.getName());
    }
}
