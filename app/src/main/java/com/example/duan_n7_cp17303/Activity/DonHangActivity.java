package com.example.duan_n7_cp17303.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_n7_cp17303.Adapter.Adapter_muahang;
import com.example.duan_n7_cp17303.DAO.Daodonhang;
import com.example.duan_n7_cp17303.DAO.Daokhachhang;
import com.example.duan_n7_cp17303.DTO.Donhang;
import com.example.duan_n7_cp17303.DTO.Khachhang;
import com.example.duan_n7_cp17303.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonHangActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private Daokhachhang daokhachhang;
    private Daodonhang daodonhang;
    private Adapter_muahang adapter_muahang;
    List<Khachhang> khachhangList = new ArrayList<>();
    List<Donhang> donhangList = new ArrayList<>();
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        findViewById(R.id.id_quaylaiHoaDon).setOnClickListener(v -> {

        });
        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        daokhachhang = new Daokhachhang();
        try {
            khachhangList = daokhachhang.get_Kh_theo_UserName(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int id_khachhang = khachhangList.get(index).getId_khachhang();
        Log.e("zzzz", String.valueOf(id_khachhang));
        recyclerView = findViewById(R.id.rclView_DonHang);
        try {
            daodonhang = new Daodonhang();
            donhangList = daodonhang.getAll_DH_theo_idKH(id_khachhang);
            adapter_muahang = new Adapter_muahang(this,donhangList);
            recyclerView.setAdapter(adapter_muahang);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}