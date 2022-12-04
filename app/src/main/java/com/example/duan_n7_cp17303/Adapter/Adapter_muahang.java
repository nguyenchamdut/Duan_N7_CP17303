package com.example.duan_n7_cp17303.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan_n7_cp17303.DAO.Daochitiethoadon;
import com.example.duan_n7_cp17303.DAO.Daokhachhang;
import com.example.duan_n7_cp17303.DAO.Daosanpham;
import com.example.duan_n7_cp17303.DTO.Chitiethoadon;
import com.example.duan_n7_cp17303.DTO.Donhang;
import com.example.duan_n7_cp17303.DTO.Khachhang;
import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class Adapter_muahang extends RecyclerView.Adapter<Adapter_muahang.viewHolderr>{
    Context context;
    List<Donhang> list;

    private Daosanpham daosanpham;
    private Daochitiethoadon daochitiethoadon;
    private Daokhachhang daokhachhang;
    private String arr[] = {"Đã được xử lý","Vận chuyển","Hoàn thành"};
    private String temp;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public Adapter_muahang(Context context, List<Donhang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_muahang, parent, false);

        viewHolderr viewHolderr = new viewHolderr(view);

        return viewHolderr;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderr holder, int position) {
        Donhang donhang = list.get(position);

        holder.tv_trangthai.setText(donhang.getTrangthai());
        holder.tv_ngaymua.setText(sdf.format(donhang.getNgay_muahang()));
        daochitiethoadon = new Daochitiethoadon();

        Chitiethoadon chitiethoadon = daochitiethoadon.getAll_CT_theo_id(donhang.getId_donhang());

        holder.tv_soluong.setText("" + chitiethoadon.getSoluong());
        int tong = chitiethoadon.getSoluong() * chitiethoadon.getGiamua();
        Log.e("zzzz", String.valueOf(tong));
        holder.tv_tonggia.setText(""+tong);
        daokhachhang = new Daokhachhang();
        Khachhang khachhang = daokhachhang.get_TK_theo_ID(donhang.getId_khachhang());
        holder.tv_tenkhachhang.setText("Tên Khách Hàng : "+khachhang.getHoten());
        holder.tv_tenkhachhang.setVisibility(View.INVISIBLE);
        daosanpham = new Daosanpham();
        try {
            Sanpham sanpham = daosanpham.get_SP_theo_ID(chitiethoadon.getId_sp());
            Glide.with(context).load(Uri.parse(sanpham.getAnh())).into(holder.imgAnhSp);
            holder.tv_tensp.setText(sanpham.getTensp());
        }catch (Exception e){
            e.printStackTrace();
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login", context.MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        if (u.equals("admin")){
            holder.tv_tenkhachhang.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Chọn Trạng Thái");
                builder.setSingleChoiceItems(arr, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        temp = arr[which];
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, ""+temp, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolderr extends RecyclerView.ViewHolder{

        ImageView imgAnhSp;
        TextView tv_tensp,tv_soluong,tv_trangthai,tv_ngaymua,tv_tonggia,tv_tenkhachhang;

        public viewHolderr(@NonNull View itemView) {
            super(itemView);
            imgAnhSp = itemView.findViewById(R.id.id_muahang_anhsp);
            tv_tensp = itemView.findViewById(R.id.id_muahang_tensp);
            tv_soluong = itemView.findViewById(R.id.id_muahang_slsp);
            tv_trangthai = itemView.findViewById(R.id.id_muahang_trangthai);
            tv_ngaymua = itemView.findViewById(R.id.id_muahang_ngaymua);
            tv_tonggia = itemView.findViewById(R.id.id_muahang_tonggia);
            tv_tenkhachhang = itemView.findViewById(R.id.id_muahang_tenkhachhang);

        }
    }
}
