package com.example.duan_n7_cp17303.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.R;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder>{
    private Context context;
    private List<Sanpham> list;

    public SanPhamAdapter(Context context, List<Sanpham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sanpham sp = list.get(position);
        holder.tvTenSP.setText("" + sp.getTensp());
        holder.tvGiaTien.setText("" + sp.getGiatien());
//        holder.imgAnhSP.

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAnhSP;
        TextView tvTenSP,tvGiaTien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnhSP = itemView.findViewById(R.id.imgSP);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTien);
        }
    }
}
