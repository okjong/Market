package com.example.market;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.zip.Inflater;

public class Tap01_Home_Fragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 pager;

    HP_PagerAdapter hp_pagerAdapter;
    String[] titles= new String[]{"추천","베스트","이벤트"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout=view.findViewById(R.id.layout_tab);
        pager=view.findViewById(R.id.pager);

        hp_pagerAdapter= new HP_PagerAdapter(getActivity());
        pager.setAdapter(hp_pagerAdapter);

        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
            }
        });

        tabLayoutMediator.attach();

    }
}




































