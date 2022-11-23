package com.example.duan_n7_cp17303.Fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_n7_cp17303.Adapter.Rec_QL_Taikhoan;
import com.example.duan_n7_cp17303.DAO.Daotaikhoan;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.R;

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

    Context context;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        RecyclerView rec = view.findViewById(R.id.rec_qltk);

        Daotaikhoan daotaikhoan = new Daotaikhoan();
        list = daotaikhoan.getAll();
        Rec_QL_Taikhoan rec_ql_taikhoan = new Rec_QL_Taikhoan(context, list);
        rec.setAdapter(rec_ql_taikhoan);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quanlytaikhoan_fragment, container, false);
    }
}
