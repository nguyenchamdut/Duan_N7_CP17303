package com.example.duan_n7_cp17303.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.R;

import java.util.ArrayList;

public class Spinner_san_pham extends ArrayAdapter<Sanpham> {

    Context context;
    ArrayList<Sanpham> list;

    public Spinner_san_pham(@NonNull Context context, ArrayList<Sanpham> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_san_pham, null);
        }

        TextView sp = view.findViewById(R.id.sp_sp);

        final Sanpham sanpham = list.get(position);

        if (sanpham != null){
            sp.setText(sanpham.getTensp());
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_san_pham, null);
        }

        TextView sp = view.findViewById(R.id.sp_sp);

        final Sanpham sanpham = list.get(position);

        if (sanpham != null){
            sp.setText(sanpham.getTensp());
        }

        return view;
    }
}
