package com.example.duan_n7_cp17303.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan_n7_cp17303.DAO.Daosanpham;
import com.example.duan_n7_cp17303.DAO.Daoyeuthich;
import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.DTO.Thongbao;
import com.example.duan_n7_cp17303.DTO.YeuThich;
import com.example.duan_n7_cp17303.R;

import java.sql.SQLException;
import java.util.List;

public class Adapter_YeuThich extends RecyclerView.Adapter<Adapter_YeuThich.viewHolderr>{
    Context context;
    List<YeuThich> list;
    Daoyeuthich daoyeuthich;
    public Adapter_YeuThich(Context context, List<YeuThich> list) {
        this.context = context;
        this.list = list;
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
        YeuThich yeuThich = list.get(position);

        Daosanpham daosanpham = new Daosanpham();

        try {
            Sanpham sanpham = daosanpham.get_SP_theo_ID(yeuThich.getId_sp());
            holder.name.setText(sanpham.getTensp());
            Glide.with(context).load(Uri.parse(sanpham.getAnh())).into(holder.img);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Bạn cần muốn bỏ yêu thích");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        daoyeuthich = new Daoyeuthich();
                        daoyeuthich.delete_yeuthich(yeuThich);
                        list.clear();
                        list = daoyeuthich.get_YT_theo_UserName(yeuThich.getUsername());
                        notifyDataSetChanged();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
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

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolderr extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name;

        public viewHolderr(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.ava_tk);
            name = itemView.findViewById(R.id.name_tk);

        }
    }
}
