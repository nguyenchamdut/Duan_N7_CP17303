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
import com.example.duan_n7_cp17303.Adapter.AdapterBinhLuan;
import com.example.duan_n7_cp17303.DAO.Daobinhluan;
import com.example.duan_n7_cp17303.DAO.Daochitiethoadon;
import com.example.duan_n7_cp17303.DAO.Daocuahang;
import com.example.duan_n7_cp17303.DAO.Daodonhang;
import com.example.duan_n7_cp17303.DAO.Daokhachhang;
import com.example.duan_n7_cp17303.DAO.Daoyeuthich;
import com.example.duan_n7_cp17303.DTO.Binhluan;
import com.example.duan_n7_cp17303.DTO.Chitiethoadon;
import com.example.duan_n7_cp17303.DTO.Cuahang;
import com.example.duan_n7_cp17303.DTO.Donhang;
import com.example.duan_n7_cp17303.DTO.Khachhang;
import com.example.duan_n7_cp17303.DTO.YeuThich;
import com.example.duan_n7_cp17303.R;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShowSPActivity extends AppCompatActivity {
    private ImageView imgBinhLuan,imgYeuThich,imgshowsp;
    private TextView tvTenSP,tvGiaTien,tvThemSP;

    private ListView lvBinhLuan;
    AdapterBinhLuan adapterBinhLuan;

    Daobinhluan daobinhluan;
    List<Binhluan> list = new ArrayList<>();
    Binhluan binhluan;

    Daoyeuthich daoyeuthich;
    int index;
    Daocuahang daocuahang;
    Daokhachhang daokhachhang;
    List<Cuahang> cuahangList = new ArrayList<>();
    List<Khachhang> khachhangList;

    List<Donhang> donhangList;
    Daodonhang daodonhang;
    Daochitiethoadon daochitiethoadon;
    Donhang donhang;


    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
        tvThemSP = findViewById(R.id.id_tvThem);
        tvTenSP.setText(tensp);
        tvGiaTien.setText(giatien);
        Glide.with(this).load(Uri.parse(imgSP)).into(imgshowsp);

        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", this.MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        Log.e("u",u);

        daoyeuthich = new Daoyeuthich();
        try {
            daobinhluan = new Daobinhluan();
            list = daobinhluan.get_BL_theo_IdSP(Integer.parseInt(id_sp));
            adapterBinhLuan = new AdapterBinhLuan(this,list);
            lvBinhLuan.setAdapter(adapterBinhLuan);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                        daobinhluan = new Daobinhluan();
                        list = daobinhluan.get_BL_theo_IdSP(Integer.parseInt(id_sp));
                        adapterBinhLuan = new AdapterBinhLuan(this,list);
                        lvBinhLuan.setAdapter(adapterBinhLuan);
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
                YeuThich yt = new YeuThich();
                yt.setUsername(u);
                yt.setId_sp(Integer.parseInt(id_sp));
                try {
                    daoyeuthich.insertYT(yt);
                    Toast.makeText(this, "Đã thêm yêu thích", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        tvThemSP.setOnClickListener(v -> {
            if(u.equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bạn cần đăng nhập để mua hàng!!");
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
                View view = inflater.inflate(R.layout.dialog_themsoluong,null);
                TextView tvSoLuong = view.findViewById(R.id.id_soluongSP);
                TextInputLayout til_donhang = view.findViewById(R.id.til_id_donhang);
                TextView id_donhang = view.findViewById(R.id.id_donhang);
                id_donhang.setVisibility(View.INVISIBLE);
                til_donhang.setVisibility(View.INVISIBLE);
                daocuahang = new Daocuahang();
                daokhachhang = new Daokhachhang();
                daodonhang = new Daodonhang();
                donhangList = daodonhang.getAll();
                if (donhangList.size() == 0){
                    id_donhang.setText("1");
                }else{
                    donhang = daodonhang.getAll().get(donhangList.size()-1);
                    id_donhang.setText(String.valueOf(donhang.getId_donhang()+1));
                }
                try {
                    khachhangList = daokhachhang.get_Kh_theo_UserName(u);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int id_khachhang = khachhangList.get(index).getId_khachhang();
                cuahangList = daocuahang.getAll();
                String tencuahang = cuahangList.get(index).getTencuahang();

                builder.setView(view);
                String datetime = sdf.format(c.getTime());
                builder.setPositiveButton("Mua Hàng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        try {

                            Donhang donhang = new Donhang();
                            donhang.setId_donhang(Integer.parseInt(id_donhang.getText().toString()));
                            donhang.setId_khachhang(id_khachhang);
                            donhang.setNgay_muahang(Date.valueOf(datetime));
                            donhang.setTrangthai("Đang Xử Lý");
                            donhang.setTencuahang(tencuahang);
                            if (daodonhang.insert_DonHang(donhang)>0){
                                daochitiethoadon = new Daochitiethoadon();
                                Chitiethoadon chitiethoadon = new Chitiethoadon();
                                chitiethoadon.setId_donhang(donhang.getId_donhang());
                                chitiethoadon.setId_sp(Integer.parseInt(id_sp));
                                chitiethoadon.setSoluong(Integer.parseInt(tvSoLuong.getText().toString()));
                                chitiethoadon.setGiamua(Integer.parseInt(giatien));

                                daochitiethoadon.insertCTHD(chitiethoadon);
                                Toast.makeText(ShowSPActivity.this, "Mua Hàng Thành Công!!", Toast.LENGTH_SHORT).show();

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

                builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
}