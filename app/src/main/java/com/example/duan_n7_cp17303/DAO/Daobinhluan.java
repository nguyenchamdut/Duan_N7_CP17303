package com.example.duan_n7_cp17303.DAO;

import android.util.Log;

import com.example.duan_n7_cp17303.DTO.Binhluan;
import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Daobinhluan {
    Connection connection;

    public Daobinhluan() {
        DbSqlServer db = new DbSqlServer();
        connection = db.openConnect();
    }
    public List<Binhluan> getAll(){
        List<Binhluan> list = new ArrayList<>();
        try {
            if (this.connection != null){
                String sqlgetAll = "select * from binhluan";

                Statement statement = this.connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sqlgetAll);
                while (resultSet.next()){
                    Binhluan bl = new Binhluan();
                    bl.setId_binhluan(resultSet.getInt("id_binhluan"));
                    bl.setId_sp(resultSet.getInt("id_sp"));
                    bl.setUsername(resultSet.getString("username"));
                    bl.setTextbinhluan(resultSet.getString("textbinhluan"));
                    list.add(bl);
                }
            }
        }catch (Exception e){
            Log.e("zzzz","getAll : co loi");
            e.printStackTrace();
        }
        return list;
    }
    public void insertBl(Binhluan bl){
        try{
            if (this.connection != null){
                String insertBL = "insert into binhluan(id_sp,username,textbinhluan) " +
                        "values (" + bl.getId_sp() + ",'" + bl.getUsername() + "',N'"+ bl.getTextbinhluan() +"')";

                String generatedColumns[] = {"id_binhluan"};

                PreparedStatement statement = this.connection.prepareStatement(insertBL,generatedColumns);
                statement.execute();
                Log.e("zzzzz","insertBL : finish insert");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzz","insertBL : co loi them du lieu");
        }
    }

    public Binhluan get_BL_theo_IdSP(int id) throws SQLException {

        List<Binhluan> list = new ArrayList<>();

        try {
            if (this.connection != null){
                String sql = "SELECT * FROM binhluan WHERE id_sp = " + id;

                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){

                    Binhluan bl = new Binhluan();
                    bl.setId_binhluan(resultSet.getInt("id_binhluan"));
                    bl.setId_sp(resultSet.getInt("id_sp"));
                    bl.setUsername(resultSet.getString("username"));
                    bl.setTextbinhluan(resultSet.getString("textbinhluan"));
                    list.add(bl);
                }

            }
        } catch (SQLException throwables) {
            Log.d("zzzz", "getThongBao: lỗi truy vấn");
            throwables.printStackTrace();

        }

        return list.get(0);
    }
}
