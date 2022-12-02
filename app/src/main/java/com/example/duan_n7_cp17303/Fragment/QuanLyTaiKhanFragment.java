package com.example.duan_n7_cp17303.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_n7_cp17303.Adapter.Rec_QL_Taikhoan;
import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuanLyTaiKhanFragment extends Fragment {

    List<Taikhoan> list = new ArrayList<>();

    public QuanLyTaiKhanFragment() {
        // Required empty public constructor
    }

    public static QuanLyTaiKhanFragment newInstance() {
        QuanLyTaiKhanFragment fragment = new QuanLyTaiKhanFragment();

        return fragment;
    }
    Daotaikhoan daotaikhoan = new Daotaikhoan();
    RecyclerView rec;
    Context context;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        rec = view.findViewById(R.id.rec_qltk);
        list = daotaikhoan.getAll();
        Rec_QL_Taikhoan rec_ql_taikhoan = new Rec_QL_Taikhoan(context, list, this);
        rec.setAdapter(rec_ql_taikhoan);

    }


    public void Del_ThongBao(String id){

        AlertDialog.Builder builder =  new AlertDialog.Builder(context);
        builder.setTitle("Delete!");
        builder.setMessage("Bạn có muốn xóa?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if (daotaikhoan.delete_taiKhoan(id) == 1){
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();

                    }else {
                        Toast.makeText(context, "ngu", Toast.LENGTH_LONG).show();

                    }

                    show();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void show(){
        list = daotaikhoan.getAll();
        Rec_QL_Taikhoan rec_ql_taikhoan = new Rec_QL_Taikhoan(context, list, this);
        rec.setAdapter(rec_ql_taikhoan);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quanlytaikhoan_fragment, container, false);
    }
}
