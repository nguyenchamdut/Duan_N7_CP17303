package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan_n7_cp17303.R;
public class ShowSPActivity extends AppCompatActivity {
    private ImageView imgBinhLuan,imgYeuThich,imgshowsp;
    private TextView tvTenSP,tvGiaTien,tvThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_spactivity);

        Intent intent1= getIntent();
        String tensp = intent1.getStringExtra("tensp");
        String giatien = intent1.getStringExtra("giatien");
        String imgSP = intent1.getStringExtra("imgsp");
        tvTenSP = findViewById(R.id.id_showtensp);
        tvGiaTien = findViewById(R.id.id_showgiatien);
        imgshowsp = findViewById(R.id.img_showsp);

        tvTenSP.setText(tensp);
        tvGiaTien.setText(giatien);
        Glide.with(this).load(Uri.parse(imgSP)).into(imgshowsp);

        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", this.MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");

        findViewById(R.id.id_quaylai).setOnClickListener(v -> {
            Intent intent = new Intent(ShowSPActivity.this,HomeActivity.class);
            startActivity(intent);
        });
        imgBinhLuan = findViewById(R.id.imgBinhLuan);
        imgBinhLuan.setOnClickListener(v -> {
            if(u.equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bạn cần đăng nhập để thêm bình luận!!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ShowSPActivity.this,Dangnhap_khach.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }else{

            }
        });
        imgYeuThich = findViewById(R.id.imgYeuThich);
        imgYeuThich.setOnClickListener(v -> {
            if(u.equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bạn cần đăng nhập để thêm yêu thích!!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ShowSPActivity.this,Dangnhap_khach.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }else{

            }
        });

    }
}