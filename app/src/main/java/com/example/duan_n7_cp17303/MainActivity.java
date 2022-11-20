package com.example.duan_n7_cp17303;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("TAG", "onCreate: luyen da o day");
        Toast.makeText(this, "Nguyen o day", Toast.LENGTH_SHORT).show();
    }
}