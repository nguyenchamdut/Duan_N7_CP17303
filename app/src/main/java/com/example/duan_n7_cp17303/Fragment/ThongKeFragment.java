package com.example.duan_n7_cp17303.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan_n7_cp17303.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class ThongKeFragment extends Fragment {

    public ThongKeFragment() {
        // Required empty public constructor
    }

    public static ThongKeFragment newInstance() {
        ThongKeFragment fragment = new ThongKeFragment();

        return fragment;
    }

    TextInputLayout tuNgay, denNgay, tongThu;
    Button doanhThu;
    ImageView fromDate, toDate;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tuNgay = view.findViewById(R.id.tuNgay);
        denNgay = view.findViewById(R.id.denNgay);
        tongThu = view.findViewById(R.id.tongThu);
        doanhThu = view.findViewById(R.id.doanhThu);
        fromDate = view.findViewById(R.id.fromdate);
        toDate = view.findViewById(R.id.todate);

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDatePicker();
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                denngay();
            }
        });

    }

    void showDialogDatePicker(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis( System.currentTimeMillis() );

         DatePickerDialog dialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // xử lý sự kiện
                        int nam = i;
                        int thang = i1; // nhận giá trị từ 0 -> 11
                        int ngay = i2;

                        tuNgay.getEditText().setText(nam + "/" + (thang + 1) + "/" + ngay);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
        );

        dialog.show();
    }

    void denngay(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis( System.currentTimeMillis() );

         DatePickerDialog dialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1; // nhận giá trị từ 0 -> 11
                        int ngay = i2;

                        denNgay.getEditText().setText(nam + "/" + (thang + 1) + "/" + ngay);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
        );

        dialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }
}