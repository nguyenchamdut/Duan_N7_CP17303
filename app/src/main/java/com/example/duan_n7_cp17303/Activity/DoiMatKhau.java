package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.R;
import com.google.android.material.textfield.TextInputLayout;

public class DoiMatKhau extends AppCompatActivity {

    TextView tvMatkhauCu, tvMatKhauMoi,tvNhaplaiMatKhauMoi;
    TextInputLayout tilMatkhauCu, tilMatKhauMoi,tilNhaplaiMatKhauMoi;
    Button btnsave , btncancel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mat_khau);

        tilMatkhauCu = findViewById(R.id.input_pass_cu);
        tilMatKhauMoi = findViewById(R.id.input_pass_moi);
        tilNhaplaiMatKhauMoi = findViewById(R.id.input_re_pass);

        btnsave = findViewById(R.id.pass_btnsave);
        btncancel = findViewById(R.id.pass_btncancel) ;


        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String p = sharedPreferences.getString("pass", "");





        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Taikhoan taikhoan = new Taikhoan();
                Daotaikhoan daotaikhoan = new Daotaikhoan();
                String matkhaucu = tilMatkhauCu.getEditText().getText().toString();
                String matkhaumoi = tilMatKhauMoi.getEditText().getText().toString();
                String rematkhaumoi = tilNhaplaiMatKhauMoi.getEditText().getText().toString();

                if (!matkhaucu.equals(p)){
                    Toast.makeText(DoiMatKhau.this, " ban can nhap dung mat khau cu", Toast.LENGTH_SHORT).show();
                }  else if (matkhaucu.length()==0 || matkhaumoi.length()==0|| rematkhaumoi.length()==0){
                    Toast.makeText(DoiMatKhau.this, "khong duoc de trong", Toast.LENGTH_SHORT).show();
                }else if (!matkhaumoi.equals(rematkhaumoi)){
                    Toast.makeText(DoiMatKhau.this, "mat khau mơi khong khơp", Toast.LENGTH_SHORT).show();
                }else {
                    taikhoan.setPass(matkhaumoi);
                    daotaikhoan.updateTaiKhoan(taikhoan);
                    Log.d("cc", "ok " + matkhaumoi);
                    Toast.makeText(DoiMatKhau.this, "ĐỔi thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
