package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan_n7_cp17303.DAO.Daokhachhang;
import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.DTO.Khachhang;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.R;

import java.util.List;


public class Themthongtin extends AppCompatActivity {
    EditText hoten, sodienthoai, email, duongpho, xa, huyen, tinh;
    Button btnthem;

    Khachhang khachhang;
    Daokhachhang daokhachhang;
    List<Taikhoan> listtaikhoan;
    List<Khachhang> khachhangList;
    String user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themthongtin);

        hoten = findViewById(R.id.hoten);
        sodienthoai = findViewById(R.id.sodienthoai);
        email = findViewById(R.id.eamil);
        duongpho = findViewById(R.id.duongpho);
        xa = findViewById(R.id.xa);
        huyen = findViewById(R.id.huyen);
        tinh = findViewById(R.id.tinh);
        btnthem = findViewById(R.id.btn_them);

        khachhang = new Khachhang();
        daokhachhang = new Daokhachhang();
        Daotaikhoan daotaikhoan = new Daotaikhoan();

        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");

        Log.d("ccc", "onCreate: " + u);

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hoten.getText().length() == 0 || sodienthoai.getText().length() == 0 || email.getText().length() ==0
                 || duongpho.getText().length() == 0 || xa.getText().length() == 0 || huyen.getText().length()==0
                 || tinh.getText().length()==0){
                    Toast.makeText(Themthongtin.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }else {
                    String duongpho_ = duongpho.getText().toString();
                    String xa_ = xa.getText().toString();
                    String huyen_ = huyen.getText().toString();
                    String tinh_ = tinh.getText().toString();
                    String diachi = duongpho_ + ", " + xa_ + ", " + huyen_ + ", " + tinh_;
                    khachhang.setUsername(u);
                    Log.d("ccc6", "onCreate: " + u);
                    khachhang.setHoten(hoten.getText().toString());
                    khachhang.setDienthoai(Integer.parseInt(sodienthoai.getText().toString()));
                    khachhang.setEmail(email.getText().toString());
                    khachhang.setDiachi(diachi);

                    daokhachhang.insertKh(khachhang);
                    Toast.makeText(Themthongtin.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}