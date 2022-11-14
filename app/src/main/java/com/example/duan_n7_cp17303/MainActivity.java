package com.example.duan_n7_cp17303;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Nguyên thêm", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Manh them", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "thêm lần 2", Toast.LENGTH_SHORT).show();
    }
}