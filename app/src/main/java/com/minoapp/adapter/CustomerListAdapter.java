package com.minoapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.minoapp.R;
import com.minoapp.data.bean.Customer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Devin on 2017/6/28.
 */

public class CustomerListAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<Customer> customers=new ArrayList<>();
    private Context mcontext;
    private LayoutInflater inflater;
    private OnItemClickListener listener;
    public CustomerListAdapter(List<Customer> customers,Context context) {
        this.customers = customers;
        this.mcontext =context;
        inflater=LayoutInflater.from(mcontext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.customer_item,parent,false);
        ViewHolder vh= new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Customer c=customers.get(position);
        holder.tv_customername.setText(customers.get(position).getName());
        holder.tv_customerarea.setText(customers.get(position).getArea());
        holder.tv_province.setText(customers.get(position).getProvince()+" "+customers.get(position).getCity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemClick(position,c);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }


    public interface OnItemClickListener{
        void onItemClick(int position, Object item);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;

    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    TextView tv_customername;
    TextView tv_province;
    TextView tv_customerarea;

    public ViewHolder(View itemView) {
        super(itemView);
        tv_customername = (TextView) itemView.findViewById(R.id.tv_customername);
        tv_customerarea = (TextView) itemView.findViewById(R.id.tv_customerarea);
        tv_province = (TextView) itemView.findViewById(R.id.tv_province);

    }

}
