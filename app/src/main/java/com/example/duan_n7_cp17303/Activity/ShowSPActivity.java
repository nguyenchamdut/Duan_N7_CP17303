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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan_n7_cp17303.DAO.Daobinhluan;
import com.example.duan_n7_cp17303.DTO.Binhluan;
import com.example.duan_n7_cp17303.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ShowSPActivity extends AppCompatActivity {
    private ImageView imgBinhLuan,imgYeuThich,imgshowsp;
    private TextView tvTenSP,tvGiaTien;

    private ListView lvBinhLuan;

    Daobinhluan daobinhluan;
    List<Binhluan> list;
    Binhluan binhluan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_spactivity);

        lvBinhLuan = findViewById(R.id.id_lvhienthibl);
        Intent intent1= getIntent();
        String id_sp = intent1.getStringExtra("id_sp");
        Log.e("idsp",String.valueOf(id_sp));
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
        Log.e("u",u);


        daobinhluan = new Daobinhluan();

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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_binhluan,null);
                TextView tvBinhLuan = view.findViewById(R.id.id_binhluan);
                TextInputLayout tilBinhLuan = view.findViewById(R.id.til_binhluan);
                Button btnHuy = view.findViewById(R.id.btnHuyBL);
                Button btnThem = view.findViewById(R.id.btnThemBL);


                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();
                btnHuy.setOnClickListener(v1 -> {
                    dialog.dismiss();
                });
                btnThem.setOnClickListener(v1 -> {
                    Binhluan bl = new Binhluan();
                    bl.setId_sp(Integer.parseInt(id_sp));
                    bl.setUsername(u);
                    bl.setTextbinhluan(tvBinhLuan.getText().toString());
                    try {
                        daobinhluan.insertBl(bl);
                        Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(this, "Them khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                });

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