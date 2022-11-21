package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.R;

public class Dangnhap_khach extends AppCompatActivity {
    EditText username, password;
    Button btn_dangnhap,btn_dangky;
    Daotaikhoan daotaikhoan;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap_khach);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btn_dangnhap = (Button) findViewById(R.id.dangnhap);
        btn_dangky = (Button) findViewById(R.id.dangky);
        daotaikhoan = new Daotaikhoan();

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Dangnhap_khach.this, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
            }
        });

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Dangky_khach.class);
                startActivity(intent);
            }
        });
    }
}