package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mat_khau);
        tvMatkhauCu = findViewById(R.id.pass_oldpass);
        tvMatKhauMoi= findViewById(R.id.pass_newpass);
        tvNhaplaiMatKhauMoi = findViewById(R.id.pass_newpasscheck);

        tilMatkhauCu = findViewById(R.id.pass_tilOldpass);
        tilMatKhauMoi = findViewById(R.id.pass_tilnewpass);
        tilNhaplaiMatKhauMoi = findViewById(R.id.pass_tilnewpasscheck);

        btnsave = findViewById(R.id.pass_btnsave);
        btncancel = findViewById(R.id.pass_btncancel) ;


        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String p = sharedPreferences.getString("pass", "");



        Taikhoan taikhoan = new Taikhoan();
        Daotaikhoan daotaikhoan = new Daotaikhoan();

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matkhaucu = tvMatkhauCu.getText().toString();
                String matkhaumoi1 = tvMatKhauMoi.getText().toString();
                String matkhaumoi2 = tvNhaplaiMatKhauMoi.getText().toString();

                if (!matkhaucu.equals(p)){
                    Toast.makeText(DoiMatKhau.this, " ban can nhap dung mat khau cu", Toast.LENGTH_SHORT).show();
                }  else if (matkhaucu.length()==0 || matkhaumoi1.length()==0|| matkhaumoi2.length()==0){
                    Toast.makeText(DoiMatKhau.this, "khong duoc de trong", Toast.LENGTH_SHORT).show();
                }else if (!matkhaumoi1.equals(matkhaumoi2)){
                    Toast.makeText(DoiMatKhau.this, "mat khau mơi khong khơp", Toast.LENGTH_SHORT).show();
                }else {
                    taikhoan.setPass(matkhaumoi1);
                    daotaikhoan.updateTaiKhoan(taikhoan);
                    Log.d("cc", "ok " + matkhaumoi1);
                }

            }
        });
    }
}
