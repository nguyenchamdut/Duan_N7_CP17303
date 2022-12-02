package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.R;

import java.util.ArrayList;
import java.util.List;

public class Dangnhap_khach extends AppCompatActivity {
    EditText username, password;
    Button btn_dangnhap,btn_dangky;
    Daotaikhoan daotaikhoan;
    ImageView btn_thoat;

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
        CheckBox checkBox = findViewById(R.id.checkk);

        btn_thoat = findViewById(R.id.btn_thoat);

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String p = sharedPreferences.getString("pass", "");
        Boolean check_login = sharedPreferences.getBoolean("remember",false);

        if (check_login){
            username.setText(u);
            password.setText(p);
            checkBox.setChecked(check_login);
        }
        else {
            username.setText("");
            password.setText("");
            checkBox.setChecked(check_login);
        }

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (daotaikhoan.check_login(user, pass) == 1){
                    remember(user,pass, checkBox.isChecked());

                    Intent intent = new Intent(Dangnhap_khach.this, HomeActivity.class);
                    Toast.makeText(Dangnhap_khach.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Dangnhap_khach.this, "Sai username or pass", Toast.LENGTH_LONG).show();

                }

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

    public void remember(String u, String p, boolean chk){
        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

            editor.putString("name", u);
            editor.putString("pass", p);
            editor.putBoolean("remember", chk);

        editor.commit();
    }

}