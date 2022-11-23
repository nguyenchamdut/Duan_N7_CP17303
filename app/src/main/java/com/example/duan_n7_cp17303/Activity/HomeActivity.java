package com.example.duan_n7_cp17303.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.duan_n7_cp17303.Fragment.TaiKhoanFragment;
import com.example.duan_n7_cp17303.Fragment.ThongBaoFragment;
import com.example.duan_n7_cp17303.Fragment.ThongKeFragment;
import com.example.duan_n7_cp17303.Fragment.TrangChuFragment;
import com.example.duan_n7_cp17303.Fragment.YeuThichFragment;
import com.example.duan_n7_cp17303.R;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.sql.Connection;

public class HomeActivity extends AppCompatActivity {
    private FragmentManager fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //chạy csdl sql server
        DbSqlServer dbSqlServer = new DbSqlServer();
        Connection conn = dbSqlServer.openConnect();

        //code giao diện
        fragment = getSupportFragmentManager();

        TrangChuFragment trangChuFragment = new TrangChuFragment();

        fragment.beginTransaction().add(R.id.id_frame,trangChuFragment).commit();

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.id_bottom_nav);

       bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id = item.getItemId();
               if (id == R.id.nav_trangchu){
                   replaceFragment(TrangChuFragment.newInstance());
               }else if(id == R.id.nav_thongbao){
                   replaceFragment(ThongBaoFragment.newInstance());
               }else if(id == R.id.nav_yeuthich){
                   replaceFragment(YeuThichFragment.newInstance());
               }else if(id == R.id.nav_taikhoan){
                   replaceFragment(TaiKhoanFragment.newInstance());
               }else if(id == R.id.nav_thongke){
                   replaceFragment(ThongKeFragment.newInstance());
               }else{
                   replaceFragment(TrangChuFragment.newInstance());
               }
               return true;
           }
       });

    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.id_frame,fragment);
        transaction.commit();
    }
}