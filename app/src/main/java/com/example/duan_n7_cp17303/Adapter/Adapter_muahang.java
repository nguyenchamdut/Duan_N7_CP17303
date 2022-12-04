package com.example.duan_n7_cp17303.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan_n7_cp17303.DAO.Daochitiethoadon;
import com.example.duan_n7_cp17303.DAO.Daosanpham;
import com.example.duan_n7_cp17303.DTO.Chitiethoadon;
import com.example.duan_n7_cp17303.DTO.Donhang;
import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class Adapter_muahang extends RecyclerView.Adapter<Adapter_muahang.viewHolderr>{
    Context context;
    List<Donhang> list;

    private Daosanpham daosanpham;
    private Daochitiethoadon daochitiethoadon;

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

        daosanpham = new Daosanpham();
        try {
            Sanpham sanpham = daosanpham.get_SP_theo_ID(chitiethoadon.getId_sp());
            Glide.with(context).load(Uri.parse(sanpham.getAnh())).into(holder.imgAnhSp);
            holder.tv_tensp.setText(sanpham.getTensp());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolderr extends RecyclerView.ViewHolder{

        ImageView imgAnhSp;
        TextView tv_tensp,tv_soluong,tv_trangthai,tv_ngaymua,tv_tonggia;

        public viewHolderr(@NonNull View itemView) {
            super(itemView);
            imgAnhSp = itemView.findViewById(R.id.id_muahang_anhsp);
            tv_tensp = itemView.findViewById(R.id.id_muahang_tensp);
            tv_soluong = itemView.findViewById(R.id.id_muahang_slsp);
            tv_trangthai = itemView.findViewById(R.id.id_muahang_trangthai);
            tv_ngaymua = itemView.findViewById(R.id.id_muahang_ngaymua);
            tv_tonggia = itemView.findViewById(R.id.id_muahang_tonggia);

        }
    }
}
