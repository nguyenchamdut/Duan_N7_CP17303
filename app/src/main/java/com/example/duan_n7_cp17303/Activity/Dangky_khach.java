package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.R;

public class Dangky_khach extends AppCompatActivity {
    EditText username, password, repassword;
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
        Taikhoan newObjCat = new Taikhoan();
        newObjCat.setAvatar("anh1");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(Dangky_khach.this, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                            Boolean insert = daotaikhoan.insertRow(user, pass);
                            if(insert==true){
                                Toast.makeText(Dangky_khach.this, "Đã đăng ký thành công", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }else{
                                Toast.makeText(Dangky_khach.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Dangky_khach.this, "Người dùng đã tồn tại! làm ơn đăng nhập", Toast.LENGTH_SHORT).show();
                        }

                } }
        });

    }
}