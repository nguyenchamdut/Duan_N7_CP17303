package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.duan_n7_cp17303.R;

public class HienthittKhach_Activity extends AppCompatActivity {
    TextView tvhoten, tvsodienthoai, tvemail, tvdiachi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_thongtinkhach);

        //ánh xạ
        tvhoten = findViewById(R.id.tv_hovaten);
        tvsodienthoai = findViewById(R.id.tv_sodienthoai);
        tvemail = findViewById(R.id.tv_email);
        tvdiachi = findViewById(R.id.tv_diachi);

        String hoten = getIntent().getExtras().getString("key_hoten");
        String sodienthoai = getIntent().getExtras().getString("key_sodienthoai");
        String email = getIntent().getExtras().getString("key_email");
        String diachi = getIntent().getExtras().getString("key_diachi");

        Log.d("thu", "onCreate: " + hoten);
        Log.d("thu", "onCreate: " + sodienthoai);
        Log.d("thu", "onCreate: " + email);
        Log.d("thu", "onCreate: " + diachi);

        tvhoten.setText(hoten);
        tvsodienthoai.setText(sodienthoai);
        tvemail.setText(email);
        tvdiachi.setText(diachi);
    }
}