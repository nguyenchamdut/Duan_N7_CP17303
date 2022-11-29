package com.example.duan_n7_cp17303.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duan_n7_cp17303.Activity.Dangnhap_khach;
import com.example.duan_n7_cp17303.Activity.SuaSPActivity;
import com.example.duan_n7_cp17303.Activity.Themthongtin;
import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.R;

import java.util.List;

public class TaiKhoanFragment extends Fragment {

    View itemview;

    TextView btn_dangnhap, btn_themthongtin,btnSuaSP;
    LinearLayout layoutSuaSP;
    public TaiKhoanFragment() {
        // Required empty public constructor
    }


    public static TaiKhoanFragment newInstance() {
        TaiKhoanFragment fragment = new TaiKhoanFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemview = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        btn_dangnhap = itemview.findViewById(R.id.ll_dangnhap);
        btn_themthongtin = itemview.findViewById(R.id.ll_thongtin);

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Dangnhap_khach.class);
                startActivity(intent);
            }
        });

        btn_themthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(), Themthongtin.class);
                startActivity(intent1);
            }
        });
        layoutSuaSP = itemview.findViewById(R.id.linear_suasp);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", getContext().MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");

        if (u.equals("admin")){
            layoutSuaSP.setVisibility(View.VISIBLE);
        }
        else {
            layoutSuaSP.setVisibility(View.INVISIBLE);
        }

        layoutSuaSP.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SuaSPActivity.class);
            startActivity(intent);
        });
        return itemview;
    }
}