package com.fhl.materialdesign;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleViewAdapter extends  RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>{
    ArrayList<String> collection;
    private Context mContext;

    public RecycleViewAdapter(Context mContext,ArrayList<String> collection) {
        this.collection = collection;
        this.mContext = mContext;
    }

    public void addCollection(ArrayList<String> strings) {
        collection.addAll(strings);
        this.notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView text1;
        public MyViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1,null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text1.setText(collection.get(position));
    }

    @Override
    public int getItemCount() {
        return collection.size();
    }
}
