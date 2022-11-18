package com.example.duan_n7_cp17303.DAO;

import android.util.Log;

import com.example.duan_n7_cp17303.DTO.Loai;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Daohang {
    Connection connection;
    public Daohang(){
        DbSqlServer db = new DbSqlServer();
        connection = db.openConnect();
    }
    public List<Loai> getAll(){
        List<Loai> loais = new ArrayList<>();
        try{
            if (this.connection != null){
                String sqlQuery = "select * from loai";

                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);
                while (resultSet.next()){
                    Loai loai = new Loai();
                    loai.setId_loai(resultSet.getInt("id_loai"));
                    loai.setTenloai(resultSet.getString("tenloai"));

                    loais.add(loai);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzz","getAll:co loi xay ra");
        }
        return loais;
    }
}
