package com.example.duan_n7_cp17303.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.DTO.Binhluan;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
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
        Daotaikhoan daotaikhoan = new Daotaikhoan();
        try {

            Taikhoan tk = daotaikhoan.get_SP_theo_User(bl.getUsername());
            tvName.setText(""+bl.getUsername());
            tvText.setText(""+bl.getTextbinhluan());
            Glide.with(context).load(Uri.parse(tk.getAvatar())).into(imgKH);
            Log.e("manh",bl.getUsername());
        }catch (Exception e){
            e.printStackTrace();
        }


        return view;
    }
}
