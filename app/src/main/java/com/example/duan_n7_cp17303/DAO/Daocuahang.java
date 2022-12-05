package com.example.duan_n7_cp17303.DAO;

import android.util.Log;

import com.example.duan_n7_cp17303.DTO.Cuahang;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Daocuahang {
    Connection connection;

    public Daocuahang() {
        DbSqlServer db = new DbSqlServer();
        connection = db.openConnect();
    }
    public List<Cuahang> getAll(){
        List<Cuahang> list = new ArrayList<>();

        try{
            if (this.connection != null){
                String sqlSanPham = "select * from cuahang";
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSanPham);
                while (resultSet.next()){
                    Cuahang cuahang = new Cuahang();
                    cuahang.setTencuahang(resultSet.getString("tencuahang"));
                    cuahang.setUsername(resultSet.getString("username"));
                    cuahang.setEmail(resultSet.getString("email"));
                    cuahang.setDienthoai(resultSet.getString("dienthoai"));
                    cuahang.setDiachi(resultSet.getString("diachi"));

                    list.add(cuahang);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzzz","getAllCH co loi");
        }
        return list;
    }

}
