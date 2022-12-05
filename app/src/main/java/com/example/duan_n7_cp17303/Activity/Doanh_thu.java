package com.example.duan_n7_cp17303.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.example.duan_n7_cp17303.DAO.Daodonhang;
import com.example.duan_n7_cp17303.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class Doanh_thu extends AppCompatActivity {

    TextInputLayout tuNgay, denNgay, tongThu;
    Button doanhThu;
    ImageView fromDate, toDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_thong_ke);


        tuNgay = findViewById(R.id.tuNgay);
        denNgay = findViewById(R.id.denNgay);
        tongThu = findViewById(R.id.tongThu);
        doanhThu = findViewById(R.id.doanhThu);
        fromDate = findViewById(R.id.fromdate);
        toDate = findViewById(R.id.todate);

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


        Daodonhang daodonhang = new Daodonhang();

        doanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongThu.getEditText().setText(" " + daodonhang.doanh_Thu(tuNgay.getEditText().getText().toString(), denNgay.getEditText().getText().toString()));
            }
        });


    }




    void showDialogDatePicker(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis( System.currentTimeMillis() );

        DatePickerDialog dialog = new DatePickerDialog(Doanh_thu.this ,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // xử lý sự kiện
                        int nam = i;
                        int thang = i1; // nhận giá trị từ 0 -> 11
                        int ngay = i2;

                        tuNgay.getEditText().setText((thang + 1) + "/" + ngay + "/" + nam);
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
                Doanh_thu.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int nam = i;
                        int thang = i1; // nhận giá trị từ 0 -> 11
                        int ngay = i2;

                        denNgay.getEditText().setText((thang + 1) + "/" + ngay + "/" + nam);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE)
        );

        dialog.show();
    }

}