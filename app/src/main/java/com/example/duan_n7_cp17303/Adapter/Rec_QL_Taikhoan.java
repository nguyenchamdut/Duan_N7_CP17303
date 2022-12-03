package com.example.duan_n7_cp17303.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_n7_cp17303.Activity.Chi_tiet_TK;
import com.example.duan_n7_cp17303.DAO.Daokhachhang;
import com.example.duan_n7_cp17303.DTO.Khachhang;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.Fragment.QuanLyTaiKhanFragment;
import com.example.duan_n7_cp17303.R;

import java.util.List;

public class Rec_QL_Taikhoan extends RecyclerView.Adapter<Rec_QL_Taikhoan.viewHolderr>{

    Context context;
    List<Taikhoan> list;
    QuanLyTaiKhanFragment fragment;

    public Rec_QL_Taikhoan(Context context, List<Taikhoan> list, QuanLyTaiKhanFragment fragment) {
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public viewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_tai_khoan, parent, false);

        viewHolderr viewHolderr = new viewHolderr(view);

        return viewHolderr;
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolderr holder, int position) {

        Taikhoan taikhoan = list.get(position);

        holder.name.setText(taikhoan.getUsername());


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.Del_ThongBao(taikhoan.getUsername());


            }
        });

        holder.tk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Daokhachhang daokhachhang = new Daokhachhang();

                if (daokhachhang.get_TK_theo_ID(taikhoan.getUsername()) != null){

                    Khachhang khachhang = daokhachhang.get_TK_theo_ID(taikhoan.getUsername());

                    Intent intent = new Intent(context, Chi_tiet_TK.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("user", taikhoan.getUsername());
                    bundle.putString("hoten", khachhang.getHoten());
                    bundle.putString("email", khachhang.getEmail());
                    bundle.putString("dienthoai", khachhang.getDienthoai());
                    bundle.putString("diachi", khachhang.getDiachi());

                    intent.putExtra("khachHang", bundle);

                    context.startActivity(intent);
                } else {

                    Intent intent = new Intent(context, Chi_tiet_TK.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("user", taikhoan.getUsername());
                    bundle.putString("hoten", "Chưa nhập thông tin");
                    bundle.putString("email", "Chưa nhập thông tin");
                    bundle.putString("dienthoai", "Chưa nhập thông tin");
                    bundle.putString("diachi", "Chưa nhập thông tin");

                    intent.putExtra("khachHang", bundle);

                    context.startActivity(intent);

                }



            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolderr extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name;
        CardView tk;

        public viewHolderr(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ava_tk);
            name = itemView.findViewById(R.id.name_tk);
            tk = itemView.findViewById(R.id.item_tk);

        }
    }
}
