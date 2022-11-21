package com.example.duan_n7_cp17303.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.duan_n7_cp17303.DTO.Loai;
import com.example.duan_n7_cp17303.R;

import java.util.ArrayList;

public class SpinnerLoai extends ArrayAdapter<Loai> {
    private Context context;
    private ArrayList<Loai> list;
    TextView tvIDloai,tvTenloai;
    public SpinnerLoai(Context context, ArrayList<Loai> list) {
        super(context,0,list);
        this.context = context;
        this.list = list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View holder = convertView;
        if (holder==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = inflater.inflate(R.layout.spinner_adapter_loai,null);

        }
        final Loai loai = list.get(position);
        if (loai != null){
            tvIDloai = holder.findViewById(R.id.item_spn_idloai);
            tvIDloai.setText(String.valueOf(loai.getId_loai()));
            tvTenloai = holder.findViewById(R.id.item_spn_idtenloai);
            tvTenloai.setText(loai.getTenloai());
        }
        return holder;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View holder = convertView;
        if (holder==null){
            LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = inflater.inflate(R.layout.spinner_adapter_loai,null);

        }
        final Loai loai = list.get(position);
        if (loai != null){
            tvIDloai = holder.findViewById(R.id.item_spn_idloai);
            tvIDloai.setText(String.valueOf(loai.getId_loai()));
            tvTenloai = holder.findViewById(R.id.item_spn_idtenloai);
            tvTenloai.setText(loai.getTenloai());
        }
        return holder;
    }
}
