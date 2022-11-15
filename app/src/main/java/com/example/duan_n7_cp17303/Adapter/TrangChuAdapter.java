package com.example.duan_n7_cp17303.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan_n7_cp17303.Fragment.AoFragment;
import com.example.duan_n7_cp17303.Fragment.KhacFragment;
import com.example.duan_n7_cp17303.Fragment.QuanFragment;

public class TrangChuAdapter extends FragmentStateAdapter {
    public TrangChuAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new AoFragment();
        }else if(position == 1){
            return  new QuanFragment();
        }else if(position == 2){
            return new KhacFragment();
        }else {
            return  new AoFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
