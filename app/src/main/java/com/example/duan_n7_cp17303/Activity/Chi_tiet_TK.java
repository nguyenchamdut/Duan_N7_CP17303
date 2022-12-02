package com.example.duan_n7_cp17303.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_n7_cp17303.R;

public class Chi_tiet_TK extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tk);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("khachHang");

        String user = bundle.getString("user");
        String hoten = bundle.getString("hoten");
        String email = bundle.getString("email");
        String dienthoai = bundle.getString("dienthoai");
        String diachi = bundle.getString("diachi");

        TextView m_user = findViewById(R.id.user);
        TextView m_hoten = findViewById(R.id.hotenn);
        TextView m_email = findViewById(R.id.emaill);
        TextView m_sdt = findViewById(R.id.sdt);
        TextView m_diachi = findViewById(R.id.diachi);

        m_user.setText(user);
        m_hoten.setText(hoten);
        m_email.setText(email);
        m_sdt.setText(""+ dienthoai);
        m_diachi.setText(diachi);







    }
}