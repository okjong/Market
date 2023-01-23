package com.example.market;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HP_PagerAdapter extends FragmentStateAdapter {

    Fragment[] fragments= new Fragment[3];


    public HP_PagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
        fragments[0]=new Hp01_purplerecommendation();
        fragments[1] = new Hp02_best();
        fragments[2] = new Hp03_week();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }


//    @NonNull
//    @Override
//    public CharSequence getPageTitle(int position){
//        return titles[position];
//    }
}
