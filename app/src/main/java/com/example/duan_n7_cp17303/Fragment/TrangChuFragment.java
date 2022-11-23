package com.example.duan_n7_cp17303.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_n7_cp17303.Activity.HomeActivity;
import com.example.duan_n7_cp17303.Activity.ThemSPActivity;
import com.example.duan_n7_cp17303.Adapter.SpinnerLoai;
import com.example.duan_n7_cp17303.Adapter.TrangChuAdapter;
import com.example.duan_n7_cp17303.DAO.Daohang;
import com.example.duan_n7_cp17303.DAO.Daosanpham;
import com.example.duan_n7_cp17303.DTO.Loai;
import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class TrangChuFragment extends Fragment {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private TrangChuAdapter adapter;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    public static TrangChuFragment newInstance() {
        TrangChuFragment fragment = new TrangChuFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 = view.findViewById(R.id.id_viewPager);
        tabLayout = view.findViewById(R.id.id_Tablayout);
        adapter = new TrangChuAdapter(getActivity());
         viewPager2.setAdapter(adapter);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0 :
                        tab.setText("Áo");
                        break;
                    case 1 :
                        tab.setText("Quần");
                        break;
                    case 2 :
                        tab.setText("Khác");
                        break;
                }
            }
        });
        mediator.attach();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", getContext().MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");

        if (u.equals("admin")){
            view.findViewById(R.id.id_fabthemsp).setVisibility(View.VISIBLE);
        }
        else {
            view.findViewById(R.id.id_fabthemsp).setVisibility(View.INVISIBLE);
        }

        view.findViewById(R.id.id_fabthemsp).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ThemSPActivity.class);
            startActivity(intent);
        });

    }

}