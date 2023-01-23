package com.example.market;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home_Recycler01_Adapter extends RecyclerView.Adapter<Home_Recycler01_Adapter.VH> {

    Context context;
    ArrayList<Home_Recycler01_item> items;

    public Home_Recycler01_Adapter(Context context, ArrayList<Home_Recycler01_item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(context).inflate(R.layout.home_recycler01_item,parent,false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Home_Recycler01_item item=items.get(position);
        holder.ivImg.setImageResource(Integer.parseInt(String.valueOf(item.imgUrl)));
        holder.tvTitle.setText(item.title);
        holder.tvPrice.setText(item.price);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView ivImg;
        TextView tvTitle;
        TextView tvPrice;

        public VH(@NonNull View itemView) {
            super(itemView);
            ivImg=itemView.findViewById(R.id.iv);
            tvTitle=itemView.findViewById(R.id.tv01);
            tvPrice=itemView.findViewById(R.id.tv02);
        }
    }
}
