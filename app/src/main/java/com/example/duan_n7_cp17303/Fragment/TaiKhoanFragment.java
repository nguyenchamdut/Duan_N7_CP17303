package com.example.duan_n7_cp17303.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_n7_cp17303.Activity.Dangnhap_khach;
import com.example.duan_n7_cp17303.Activity.HienthittKhach_Activity;
import com.example.duan_n7_cp17303.Activity.HomeActivity;
import com.example.duan_n7_cp17303.Activity.SuaSPActivity;
import com.example.duan_n7_cp17303.Activity.Themthongtin;
import com.example.duan_n7_cp17303.DAO.Daokhachhang;
import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.DTO.Khachhang;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.R;

import java.util.List;

public class TaiKhoanFragment extends Fragment {

    View itemview;
    Daokhachhang daokhachhang;
    List<Khachhang> khachhangList;
    TextView btn_dangnhap, btn_themthongtin, tvtentaikhoan;

    LinearLayout layoutSuaSP;
    public TaiKhoanFragment() {
        // Required empty public constructor
    }


    public static TaiKhoanFragment newInstance() {
        TaiKhoanFragment fragment = new TaiKhoanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        itemview = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        btn_dangnhap = itemview.findViewById(R.id.ll_dangnhap);
        btn_themthongtin = itemview.findViewById(R.id.ll_thongtin);
        tvtentaikhoan = itemview.findViewById(R.id.tv_tentaikhoan);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String p = sharedPreferences.getString("pass", "");

        if (u.equals("") || p.equals("")){
            btn_dangnhap.setText("Đăng nhập");
        }else {
            btn_dangnhap.setText("Đăng Xuất");
            tvtentaikhoan.setText(u);
        }

        btn_themthongtin.setText("Thông tin khách hang");

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangxuat();
            }
        });

        btn_themthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttkhachhang();
            }
        });
        layoutSuaSP = itemview.findViewById(R.id.linear_suasp);

        if (u.equals("admin")){
            layoutSuaSP.setVisibility(View.VISIBLE);
        }
        else {
            layoutSuaSP.setVisibility(View.INVISIBLE);
        }

        layoutSuaSP.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SuaSPActivity.class);
            startActivity(intent);
        });
        return itemview;
    }

    void dangxuat(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String p = sharedPreferences.getString("pass", "");

        if (u.equals("") || p.equals("")){
            Log.d("TAG", "dangnhap");
            Intent intent = new Intent(getContext(), Dangnhap_khach.class);
            startActivity(intent);
        }else {
            Log.d("TAG", "dang xuat");
            Toast.makeText(getContext(), "Bạn đã đăng xuất thành công", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(getContext(), HomeActivity.class);
            startActivity(intent);
        }
    }

    void ttkhachhang(){
        daokhachhang = new Daokhachhang();
        khachhangList = daokhachhang.getAll();
        Log.d("n", "ttkhachhang: " + khachhangList);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");

        if (u.equals("")){
            //Chưa đăng nhập cần đăng nhập
            Toast.makeText(getContext(), "Bạn cần phải đăng nhập", Toast.LENGTH_SHORT).show();
        } else if (!u.equals("")) {
            for (int i = 0; i < khachhangList.size(); i++) {
                Khachhang khachhang = khachhangList.get(i);
                Log.d("cc", "ttkhachhang: " + khachhang.getUsername() + " ," + khachhang.getId_khachhang());
                if (khachhang.getUsername().equals(u)) {
                    Log.d("cc", "Đã chạy vào if này: ");

                    SharedPreferences pref = getContext().getSharedPreferences("MyPref", MODE_PRIVATE); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("key_name", khachhang.getUsername());
                    editor.putInt("key_id", khachhang.getId_khachhang());
                    editor.putString("key_hoten", khachhang.getHoten());
                    editor.putString("key_sodienthoai", String.valueOf(khachhang.getDienthoai()));
                    editor.putString("key_email", khachhang.getEmail());
                    editor.putString("key_diachi", khachhang.getDiachi());
                    editor.commit();

                }
            }

            SharedPreferences gg = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            String id_khachhang = String.valueOf(gg.getInt("key_id", 0));
            String usertt = gg.getString("key_name", "");
            String hoten = gg.getString("key_hoten", "");
            String sodienthoai = gg.getString("key_sodienthoai", "");
            String email = gg.getString("key_email", "");
            String diachi = gg.getString("key_diachi", "");

            Log.d("cc", "Đang gọi id khách hàng: " + id_khachhang);
            Log.d("cc", "Đang gọi username khach hang:  " + usertt);
            if (usertt.equals(u)) {
                //Hiển thị thông tin khách hàng
                Intent intent = new Intent(getContext(), HienthittKhach_Activity.class);
                intent.putExtra("key_id", id_khachhang);
                intent.putExtra("key_hoten", hoten);
                intent.putExtra("key_sodienthoai", sodienthoai);
                intent.putExtra("key_email", email);
                intent.putExtra("key_diachi", diachi);
                startActivity(intent);
                Log.d("cc", "Đã hiển thị thông tin khách hàng: ");
            } else {
                //thêm thông tin khách hàng
                Log.d("cc", "Cần thêm thông tin khách hàng: ");
                Intent intent1 = new Intent(getContext(), Themthongtin.class);
                startActivity(intent1);
            }

        }
}
}