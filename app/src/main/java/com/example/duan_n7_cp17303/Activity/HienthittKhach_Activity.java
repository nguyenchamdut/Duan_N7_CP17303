package com.example.duan_n7_cp17303.Activity;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_n7_cp17303.DAO.Daokhachhang;
import com.example.duan_n7_cp17303.DTO.Khachhang;
import com.example.duan_n7_cp17303.Fragment.TaiKhoanFragment;
import com.example.duan_n7_cp17303.R;
import com.google.android.material.textfield.TextInputLayout;

public class HienthittKhach_Activity extends AppCompatActivity {
    TextView tvhoten, tvsodienthoai, tvemail, tvdiachi, btnsuattkhach;
    ImageView btn_thoat;
    TextInputLayout texthoten, textsodienthoai, textemail, textduong, textxa, texthuyen, texttinh;
    Button btnsua, btnhuy;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_thongtinkhach);

        //ánh xạ
        tvhoten = findViewById(R.id.tv_hovaten);
        tvsodienthoai = findViewById(R.id.tv_sodienthoai);
        tvemail = findViewById(R.id.tv_email);
        tvdiachi = findViewById(R.id.tv_diachi);
        btn_thoat = findViewById(R.id.btn_thoat);
        btnsuattkhach = findViewById(R.id.sua_ttkhach);


        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        String hoten = getIntent().getExtras().getString("key_hoten");
        String sodienthoai = getIntent().getExtras().getString("key_sodienthoai");
        String email = getIntent().getExtras().getString("key_email");
        String diachi = getIntent().getExtras().getString("key_diachi");

        Log.d("thu", "onCreate: " + hoten);
        Log.d("thu", "onCreate: " + sodienthoai);
        Log.d("thu", "onCreate: " + email);
        Log.d("thu", "onCreate: " + diachi);

        tvhoten.setText(hoten);
        tvsodienthoai.setText(sodienthoai);
        tvemail.setText(email);
        tvdiachi.setText(diachi);

        btnsuattkhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogsuattkhach();
            }
        });
    }

    void dialogsuattkhach(){

        // lấy username vs id_khachhang
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String id_khach = getIntent().getExtras().getString("key_id");
        Log.d("thu", "onCreate: " + id_khach);

        //Khỏi tạo dialog
        Dialog dialog = new Dialog(HienthittKhach_Activity.this);
        dialog.setContentView(R.layout.dialog_sua_ttkhach);

        //ánh xạ
        btnsua = dialog.findViewById(R.id.btn_sua);
        btnhuy = dialog.findViewById(R.id.btn_huy);
        textemail = dialog.findViewById(R.id.input_email);
        texthoten = dialog.findViewById(R.id.input_hoten);
        textsodienthoai = dialog.findViewById(R.id.input_sodienthoai);
        textduong = dialog.findViewById(R.id.input_duong);
        textxa = dialog.findViewById(R.id.input_xa);
        texthuyen = dialog.findViewById(R.id.input_huyen);
        texttinh = dialog.findViewById(R.id.input_tinh);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

        Daokhachhang daokhachhang = new Daokhachhang();
        Khachhang khachhang = new Khachhang();

        //nút hủy để ẩn dialog đi
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HienthittKhach_Activity.this, "Hủy sửa thông tin", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = texthoten.getEditText().getText().toString();
                String email = textemail.getEditText().getText().toString();
                String sodienthoai = textsodienthoai.getEditText().getText().toString();
                String duong = textduong.getEditText().getText().toString();
                String xa = textxa.getEditText().getText().toString();
                String huyen = texthuyen.getEditText().getText().toString();
                String tinh = texttinh.getEditText().getText().toString();

                String diachi = duong + ", " + xa + ", " + huyen + ", " + tinh;

                //kiểm tra đã nhận dữ liệu từ ô nhập chưa
                Log.d("text", "ô nhập từ dialog: " + hoten);
                Log.d("text", "ô nhập từ dialog: " + email);
                Log.d("text", "ô nhập từ dialog: " + sodienthoai);
                Log.d("text", "ô nhập từ dialog: " + diachi);

                khachhang.setId_khachhang(Integer.parseInt(id_khach));
                khachhang.setUsername(u);
                khachhang.setHoten(hoten);
                khachhang.setEmail(email);
                khachhang.setDienthoai(sodienthoai);
                khachhang.setDiachi(diachi);

                daokhachhang.updateRow(khachhang);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}