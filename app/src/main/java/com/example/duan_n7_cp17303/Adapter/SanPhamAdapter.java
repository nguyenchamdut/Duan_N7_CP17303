package com.example.duan_n7_cp17303.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan_n7_cp17303.Activity.ShowSPActivity;
import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.R;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends BaseAdapter {
    private Context context;
    private List<Sanpham> list;

    public SanPhamAdapter(Context context, List<Sanpham> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = View.inflate(parent.getContext(),R.layout.item_sanpham,null);
        }else{
            view = convertView;
        }
        TextView tvTenSP = view.findViewById(R.id.tvTenSP);
        TextView tvGiaTien = view.findViewById(R.id.tvGiaTien);
        ImageView imgAnhSP = view.findViewById(R.id.imgSP);

        Sanpham sp = list.get(position);
        tvTenSP.setText("" + sp.getTensp());
        tvGiaTien.setText("" + sp.getGiatien());
        Glide.with(context).load(Uri.parse(sp.getAnh())).into(imgAnhSP);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context,ShowSPActivity.class);
                context.startActivity(intent);
            }
        });
        return view;
    }




}
