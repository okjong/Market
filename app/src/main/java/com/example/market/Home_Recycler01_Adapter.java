package com.example.market;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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

        String imgUrl="http://jeilpharm.dothome.co.kr/Market/"+item.file;
        Glide.with(context).load(imgUrl).into(holder.ivImg);

        holder.tvTitle.setText(item.name);
        holder.tvPrice.setText(item.price+"원");
        holder.tvNickName.setText("판매자 : "+item.nickName);
        holder.ivImg.setTag(imgUrl);

        //로그인정보아이디 변수에넣기
        String userId=(item.id);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView ivImg;
        TextView tvTitle;
        TextView tvPrice;
        TextView tvNickName;

        public VH(@NonNull View itemView) {
            super(itemView);
            ivImg=itemView.findViewById(R.id.iv);
            tvTitle=itemView.findViewById(R.id.tv01);
            tvPrice=itemView.findViewById(R.id.tv02);
            tvNickName=itemView.findViewById(R.id.tv03);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();
                    Home_Recycler01_item item = items.get(pos);
                    // 1. 글로벌에 저장
                    G.selectedItem = items.get(pos);
                    // 2-1. Data Json으로 변환해서 Intent로 전달
//                    Gson gson = new Gson();
//                    String json = gson.toJson(item);
                    // 2-2. Data의 각자 값을 Intent로 전달
                    String userId = item.id;
                    String title =item.name;
                    String price =item.price;
                    String nick=item.nickName;



                    String img = (String)ivImg.getTag();

                    Intent intent= new Intent(context,DetailActivity.class);

//                    intent.putExtra("data", json);
                    intent.putExtra("title",title);
                    intent.putExtra("price",price);
                    intent.putExtra("nick",nick);
                    intent.putExtra("img",img);
                    intent.putExtra("userId",userId);

                    context.startActivity(intent);
                }
            });
        }
    }


}
