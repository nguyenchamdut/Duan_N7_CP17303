package com.example.duan_n7_cp17303.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan_n7_cp17303.DTO.Binhluan;
import com.example.duan_n7_cp17303.R;

import java.util.List;

public class AdapterBinhLuan extends BaseAdapter {
    private Context context;
    private List<Binhluan> list;

    public AdapterBinhLuan(Context context, List<Binhluan> list) {
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
            view = View.inflate(parent.getContext(), R.layout.item_binhluan,null);
        }else{
            view = convertView;
        }
        TextView tvName = view.findViewById(R.id.tvUserNameBL);
        TextView tvText = view.findViewById(R.id.tvTextBL);
        ImageView imgKH = view.findViewById(R.id.imgKH);


        Binhluan bl = list.get(position);
        tvName.setText(""+bl.getUsername());
        tvText.setText(""+bl.getTextbinhluan());

        return view;
    }
}
