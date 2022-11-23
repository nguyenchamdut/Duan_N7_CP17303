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
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);

        daotaikhoan = new Daotaikhoan();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Taikhoan taikhoan = new Taikhoan();
                taikhoan.setUsername(username.getText().toString());
                taikhoan.setPass(password.getText().toString());
                    try {
                        daotaikhoan.insertRow(taikhoan);
                        Toast.makeText(Dangky_khach.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(Dangky_khach.this, "Them khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }

}