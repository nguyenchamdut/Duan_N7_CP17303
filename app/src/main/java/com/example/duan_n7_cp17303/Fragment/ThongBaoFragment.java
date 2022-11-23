package com.example.duan_n7_cp17303.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan_n7_cp17303.Adapter.Rec_Adapter_ThongBao;
import com.example.duan_n7_cp17303.Adapter.Spinner_san_pham;
import com.example.duan_n7_cp17303.DAO.DaoThongBao;
import com.example.duan_n7_cp17303.DAO.Daosanpham;
import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.DTO.Thongbao;
import com.example.duan_n7_cp17303.R;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThongBaoFragment extends Fragment {

    public ThongBaoFragment() {
        // Required empty public constructor
    }

    public static ThongBaoFragment newInstance() {
        ThongBaoFragment fragment = new ThongBaoFragment();

        return fragment;
    }

    Context context;
    List<Thongbao> list = new ArrayList<>();
    DaoThongBao daoThongBao;
    Daosanpham daosanpham;
    List<Sanpham> list_sp = new ArrayList<>();
    String tenSp;
    int id_sp;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        RecyclerView rec_tb = view.findViewById(R.id.rec_tb);
        ImageView fab_tb = view.findViewById(R.id.fab_tb);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view1 = inflater.inflate(R.layout.dialog_thong_bao, view.findViewById(R.id.dl_tb));

        Spinner sp_dh = view1.findViewById(R.id.tb_donHang);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", getContext().MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");

        if (u.equals("admin")){
            fab_tb.setVisibility(View.VISIBLE);
        }
        else {
            fab_tb.setVisibility(View.INVISIBLE);
        }

        TextInputEditText title = view1.findViewById(R.id.tieuDe_tb);
        TextInputEditText chiTiet = view1.findViewById(R.id.chiTiet_tb);

        daosanpham = new Daosanpham();
        list_sp = daosanpham.getAll();
        Spinner_san_pham spinner_san_pham = new Spinner_san_pham(context, (ArrayList<Sanpham>) list_sp);
        sp_dh.setAdapter(spinner_san_pham);

        sp_dh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_sp = list_sp.get(position).getId_sp();
                tenSp = list_sp.get(position).getTensp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        daoThongBao = new DaoThongBao();
        list = daoThongBao.getThongBao();
        Rec_Adapter_ThongBao adapter = new Rec_Adapter_ThongBao(list, context);
        rec_tb.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        rec_tb.addItemDecoration(itemDecoration);


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view1);

        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                try {

                    Thongbao thongBao = new Thongbao();
                    thongBao.setId_sp(id_sp);
                    thongBao.setTieude(title.getText().toString());
                    thongBao.setChitiettieude(chiTiet.getText().toString());

                    int kq = daoThongBao.insert_thongBao(thongBao);

                    if (kq == 1){
                        Log.d("zzzzzzzzz", "onClick: thanh cong");
                    }else {
                        Toast.makeText(getContext(), "fail", Toast.LENGTH_LONG).show();

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                daoThongBao = new DaoThongBao();
                list = daoThongBao.getThongBao();
                Rec_Adapter_ThongBao adapter = new Rec_Adapter_ThongBao(list, context);
                rec_tb.setAdapter(adapter);

                dialog.dismiss();
            }
        });


        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();


        fab_tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_bao, container, false);

    }
}