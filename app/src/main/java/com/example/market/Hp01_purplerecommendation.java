package com.example.market;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Hp01_purplerecommendation extends Fragment {

    ArrayList<Home_Recycler01_item> items= new ArrayList<Home_Recycler01_item>();
    RecyclerView recyclerView;
    Home_Recycler01_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_page01_purplerecommendation,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        items.add(new Home_Recycler01_item(R.drawable.kurlymarket,"김치","11500"));
//        items.add(new Home_Recycler01_item(R.drawable.kurlymarket,"양상추","1000"));
//        items.add(new Home_Recycler01_item(R.drawable.kurlymarket,"돼지고기","51500"));
//        items.add(new Home_Recycler01_item(R.drawable.kurlymarket,"미역","22500"));
//        items.add(new Home_Recycler01_item(R.drawable.kurlymarket,"김치","11500"));
//        items.add(new Home_Recycler01_item(R.drawable.kurlymarket,"양상추","1000"));
//        items.add(new Home_Recycler01_item(R.drawable.kurlymarket,"돼지고기","51500"));
//        items.add(new Home_Recycler01_item(R.drawable.kurlymarket,"미역","22500"));

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView= view.findViewById(R.id.recycler);
        adapter=new Home_Recycler01_Adapter(getContext(),items);
        recyclerView.setAdapter(adapter);

    }
}
