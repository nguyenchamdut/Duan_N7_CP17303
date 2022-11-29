package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.duan_n7_cp17303.Adapter.AdapterSuaSP;
import com.example.duan_n7_cp17303.DAO.Daosanpham;
import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.R;

import java.util.ArrayList;
import java.util.List;

public class SuaSPActivity extends AppCompatActivity {
    List<Sanpham> list = new ArrayList<>();
    AdapterSuaSP adapterSuaSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_spactivity);
        findViewById(R.id.id_quaylaiSUA).setOnClickListener(v -> {
            Intent intent = new Intent(SuaSPActivity.this,HomeActivity.class);
            startActivity(intent);
        });
        RecyclerView recyclerView = findViewById(R.id.id_RecyclerSuaSP);
        Daosanpham dao = new Daosanpham();
        list = dao.getAll();
        adapterSuaSP = new AdapterSuaSP(list,this);
        LinearLayoutManager manager = new LinearLayoutManager(SuaSPActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterSuaSP);

    }
}