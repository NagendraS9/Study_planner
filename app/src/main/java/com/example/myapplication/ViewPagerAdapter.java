package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.tab1.first_tab;
import com.example.myapplication.tab2.second_tab;
import com.example.myapplication.tab3.third_tab;
import com.example.myapplication.tab4.fourth_tab;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new first_tab();
            case 1:
                return new second_tab();
            case 2:
                return new third_tab();
            default:
                return new fourth_tab();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
