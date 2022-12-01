package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    ImageView btn_thoat;
    Khachhang khachhang;
    Daokhachhang daokhachhang;


    int temp = 0;
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
        btn_thoat = findViewById(R.id.btn_thoat);

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        khachhang = new Khachhang();
        daokhachhang = new Daokhachhang();
        Daotaikhoan daotaikhoan = new Daotaikhoan();

        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        Log.d("ccc", "onCreate: " + u);

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    validate();
                    if (temp == 0){
                        String duongpho_ = duongpho.getText().toString();
                        String xa_ = xa.getText().toString();
                        String huyen_ = huyen.getText().toString();
                        String tinh_ = tinh.getText().toString();
                        String diachi = duongpho_ + ", " + xa_ + ", " + huyen_ + ", " + tinh_;
                        khachhang.setUsername(u);
                        Log.d("ccc6", "onCreate: " + u);
                        khachhang.setHoten(hoten.getText().toString());
                        khachhang.setDienthoai(sodienthoai.getText().toString());
                        khachhang.setEmail(email.getText().toString());
                        khachhang.setDiachi(diachi);
                        Log.d("tt", "onClick: " + sodienthoai.getText().toString());
                        /*daokhachhang.insertKh(khachhang);*/
                        Toast.makeText(Themthongtin.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        temp = 0;
                    }
            }
        });
    }
    public void validate(){
        String phone = sodienthoai.getText().toString();
        String mail = email.getText().toString();
        if(hoten.getText().length() == 0){
            Toast.makeText(this, "Không để trống họ tên", Toast.LENGTH_SHORT).show();
            temp++;
        }
        if (sodienthoai.getText().length() == 0){
            Toast.makeText(this, "Không để trống số điện thoại", Toast.LENGTH_SHORT).show();
            temp++;
        }else if(!phone.matches("^0\\d{9}$")){
            Toast.makeText(this, "Không đúng định dạng số điện thoại", Toast.LENGTH_SHORT).show();
            temp++;
        }
        if (email.getText().length() == 0){
            Toast.makeText(this, "Không để trống email", Toast.LENGTH_SHORT).show();
            temp++;
        }else if(!mail.matches("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")){
            Toast.makeText(this, "Không đúng định dạng email", Toast.LENGTH_SHORT).show();
            temp++;
        }
        if (duongpho.getText().length() == 0){
            Toast.makeText(this, "Không để trống tên đường", Toast.LENGTH_SHORT).show();
            temp++;
        }
        if (xa.getText().length() == 0){
            Toast.makeText(this, "Không để trống tên xã", Toast.LENGTH_SHORT).show();
            temp++;
        }
        if (huyen.getText().length() == 0){
            Toast.makeText(this, "Không để trống tên huyện", Toast.LENGTH_SHORT).show();
            temp++;
        }
        if (tinh.getText().length() == 0){
            Toast.makeText(this, "Không để trống tên tỉnh", Toast.LENGTH_SHORT).show();
            temp++;
        }
    }
}