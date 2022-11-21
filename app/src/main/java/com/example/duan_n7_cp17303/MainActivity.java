package com.example.duan_n7_cp17303;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.duan_n7_cp17303.DAO.Daohang;
import com.example.duan_n7_cp17303.DTO.Loai;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


        Daohang catDao = new Daohang();
        List<Loai> list = catDao.getAll(); // lấy danh sách
// duyệt mảng in ra danh sách

        for(int i = 0; i<list.size(); i++){
            Loai objCat = list.get(i);
            Log.e("zzzz","onCreate : phan tu thu " + i + ": id = "+objCat.getId_loai()+ ", name : "+objCat.getTenloai());
        }

    }
}