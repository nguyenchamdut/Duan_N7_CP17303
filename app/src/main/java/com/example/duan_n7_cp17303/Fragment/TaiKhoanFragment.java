package com.example.duan_n7_cp17303.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.duan_n7_cp17303.Activity.Dangnhap_khach;
import com.example.duan_n7_cp17303.Activity.DoiMatKhau;
import com.example.duan_n7_cp17303.Activity.HienthittKhach_Activity;
import com.example.duan_n7_cp17303.Activity.HomeActivity;
import com.example.duan_n7_cp17303.Activity.SuaSPActivity;
import com.example.duan_n7_cp17303.Activity.Themthongtin;
import com.example.duan_n7_cp17303.DAO.Daokhachhang;
import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.DTO.Khachhang;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class TaiKhoanFragment extends Fragment {
    LinearLayout llthemthongtin, lldonhang, lldangnhap, lldoimatkhau,llsanphamdathem;
    View itemview;
    Daokhachhang daokhachhang;
    List<Khachhang> khachhangList;
    TextView tvdangnhap, tvthemthongtin, tvtentaikhoan, tvdoimatkhau;
    ImageView avatar;
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
        tvdangnhap = itemview.findViewById(R.id.tv_dangnhap);
        tvthemthongtin = itemview.findViewById(R.id.tv_thongtin);
        tvtentaikhoan = itemview.findViewById(R.id.tv_tentaikhoan);
        tvdoimatkhau = itemview.findViewById(R.id.tv_doimatkhau);

        lldoimatkhau = itemview.findViewById(R.id.ll_doimatkhau);
        llthemthongtin = itemview.findViewById(R.id.ll_thongtin);
        lldonhang = itemview.findViewById(R.id.ll_donhang);
        lldangnhap = itemview.findViewById(R.id.ll_dangnhap);
        avatar = itemview.findViewById(R.id.avatar);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        String p = sharedPreferences.getString("pass", "");

        if (u.equals("") || p.equals("")){
            tvdangnhap.setText("Đăng nhập");
            lldoimatkhau.setVisibility(View.INVISIBLE);
        }else {
            tvdangnhap.setText("Đăng Xuất");
            tvtentaikhoan.setText(u);
        }

        tvthemthongtin.setText("Thông tin khách hang");

        lldangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangxuat();
            }
        });

        llthemthongtin.setOnClickListener(new View.OnClickListener() {
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

        lldoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DoiMatKhau.class);
                startActivity(intent);
            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAvatar();
            }
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
            String usertt = gg.getString("key_name", "");
            String id_khachhang = String.valueOf(gg.getInt("key_id", 0));
            String hoten = gg.getString("key_hoten", "");
            String sodienthoai = gg.getString("key_sodienthoai", "");
            String email = gg.getString("key_email", "");
            String diachi = gg.getString("key_diachi", "");

            Log.d("cc", "Đang gọi username khach hang:  " + usertt);
            if (usertt.equals(u)) {
                //Hiển thị thông tin khách hàng
                Intent intent = new Intent(getContext(), HienthittKhach_Activity.class);
                intent.putExtra("key_hoten", hoten);
                intent.putExtra("key_id", id_khachhang);
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

    void dialogAvatar(){
        //lấy username
        SharedPreferences preferences = getContext().getSharedPreferences("Login", MODE_PRIVATE);
        String u = preferences.getString("name", "");

        //Khởi tạo dialog
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_avatar);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);

        //ánh xạ
        TextInputLayout layoutavatar = dialog.findViewById(R.id.layout_avatar);
        Button btnthem = dialog.findViewById(R.id.them_avatar);
        Button btnhuy = dialog.findViewById(R.id.huy_avatar);

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Daotaikhoan daotaikhoan = new Daotaikhoan();
        Taikhoan taikhoan = new Taikhoan();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link_avatar = layoutavatar.getEditText().getText().toString();

                taikhoan.setUsername(u);
                taikhoan.setAvatar(link_avatar);
                daotaikhoan.updateavatar(taikhoan);
                dialog.dismiss();
                Toast.makeText(getContext(), "Sửa avatar thành công", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

}
