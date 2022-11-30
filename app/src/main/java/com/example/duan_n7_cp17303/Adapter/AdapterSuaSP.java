package com.example.duan_n7_cp17303.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan_n7_cp17303.DAO.Daosanpham;
import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.R;

import java.util.List;

public class AdapterSuaSP extends RecyclerView.Adapter<AdapterSuaSP.viewHolderr>{
    List<Sanpham> list;
    Context context;

    public AdapterSuaSP(List<Sanpham> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setData(List<Sanpham> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public viewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_suasp, parent, false);

       viewHolderr viewHolderr = new viewHolderr(view);

        return viewHolderr;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderr holder, int position) {
        Sanpham sp =list.get(position);
        holder.tv_tenspsua.setText(sp.getTensp());
        holder.tv_giatiensua.setText(sp.getGiatien());
        Glide.with(context).load(Uri.parse(sp.getAnh())).into(holder.img_spsua);


        holder.itemView.setOnClickListener(v -> {
            dialogSuaSP(sp);
        });

    }
    public void dialogSuaSP(Sanpham sp){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsp,null);
        TextView tvLoaiSP = view.findViewById(R.id.id_loaispSUA);
        TextView tvTenSP = view.findViewById(R.id.id_tenspSUA);
        TextView tvGiaTien = view.findViewById(R.id.id_giatienSUA);
        TextView tvSL = view.findViewById(R.id.id_soluongSUA);
        TextView tvAnh = view.findViewById(R.id.id_anhspSUA);
        Button btnHuy = view.findViewById(R.id.id_btnhuySUA);
        Button btnSua = view.findViewById(R.id.id_btnthemSUA);
        tvLoaiSP.setText(String.valueOf(sp.getId_loai()));
        tvTenSP.setText(sp.getTensp());
        tvGiaTien.setText(sp.getGiatien());
        tvSL.setText(String.valueOf(sp.getSoluong()));
        tvAnh.setText(sp.getAnh());
        int id_sp = sp.getId_sp();
        Log.e("manh",String.valueOf(id_sp));
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
         btnHuy.setOnClickListener(v -> {
             dialog.dismiss();
         });
         btnSua.setOnClickListener(v -> {
             String loaisp = tvLoaiSP.getText().toString();
             String tensp = tvTenSP.getText().toString();
             String giatien = tvGiaTien.getText().toString();
             String soluong = tvSL.getText().toString();
             String anh = tvAnh.getText().toString();
//             sp.setId_sp(id_sp);
             sp.setTensp(tensp);
             sp.setId_loai(Integer.parseInt(loaisp));
             sp.setGiatien(giatien);
             sp.setSoluong(Integer.parseInt(soluong));
             sp.setAnh(anh);
             Daosanpham dao = new Daosanpham();
             dao.updateSP(sp);
             Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
             setData(list);
             dialog.dismiss();
         });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolderr extends RecyclerView.ViewHolder{

        ImageView img_spsua;
        TextView tv_tenspsua, tv_giatiensua;

        public viewHolderr(@NonNull View itemView) {
            super(itemView);

            img_spsua = itemView.findViewById(R.id.imgSPSUA);
            tv_tenspsua = itemView.findViewById(R.id.tvTenSPSUA);
            tv_giatiensua = itemView.findViewById(R.id.tvGiaTienSUA);

        }
    }
}
