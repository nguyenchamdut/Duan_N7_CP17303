package com.example.duan_n7_cp17303.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.R;

import java.util.List;

public class Rec_QL_Taikhoan  extends RecyclerView.Adapter<Rec_QL_Taikhoan.viewHolderr>{

    Context context;
    List<Taikhoan> list;

    public Rec_QL_Taikhoan(Context context, List<Taikhoan> list) {
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

        Taikhoan taikhoan = list.get(position);

        //holder.img
        holder.name.setText(taikhoan.getUsername());

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
