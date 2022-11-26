package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.R;

public class Dangky_khach extends AppCompatActivity {
    EditText username, password, repassword,avatar;
    Button signup;
    Daotaikhoan daotaikhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky_khach);
        username =  findViewById(R.id.username);
        password =  findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signup = findViewById(R.id.btnsignup);

        daotaikhoan = new Daotaikhoan();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString();

                Taikhoan taikhoan = new Taikhoan();
                taikhoan.setUsername(username.getText().toString());
                taikhoan.setPass(password.getText().toString());
                if (username.getText().length() == 0 || password.getText().length() == 0  || repassword.getText().length() == 0 ){
                    Toast.makeText(Dangky_khach.this, "không được để trống", Toast.LENGTH_SHORT).show();
                }else if (daotaikhoan.check_login(user, pass) == 1){
                    Toast.makeText(Dangky_khach.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                }else {
                    daotaikhoan.insertRow(taikhoan);
                    Toast.makeText(Dangky_khach.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                }

        });
    }

}