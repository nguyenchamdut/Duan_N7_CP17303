package com.example.duan_n7_cp17303.DAO;

import android.util.Log;

import com.example.duan_n7_cp17303.DTO.Binhluan;
import com.example.duan_n7_cp17303.DTO.YeuThich;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Daoyeuthich {
    Connection connection;

    public Daoyeuthich() {
        DbSqlServer db = new DbSqlServer();
        connection = db.openConnect();
    }
    public List<YeuThich> getAll(){
        List<YeuThich> list = new ArrayList<>();
        try {
            if (this.connection != null){
                String sqlgetAll = "select * from yeuthich";

                Statement statement = this.connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sqlgetAll);
                while (resultSet.next()){
                    YeuThich yt = new YeuThich();
                    yt.setId_yeuthich(resultSet.getInt("id_yeuthich"));
                    yt.setUsername(resultSet.getString("username"));
                    yt.setId_sp(resultSet.getInt("id_sp"));

                    list.add(yt);
                }
            }
        }catch (Exception e){
            Log.e("zzzz","getAllYT : co loi");
            e.printStackTrace();
        }
        return list;
    }
    public void insertYT(YeuThich yt){
        try{
            if (this.connection != null){
                String insertBL = "insert into yeuthich(username,id_sp) " +
                        "values ('" + yt.getUsername() + "',"+ yt.getId_sp() +")";

                String generatedColumns[] = {"id_yeuthich"};

                PreparedStatement statement = this.connection.prepareStatement(insertBL,generatedColumns);
                statement.execute();
                Log.e("zzzzz","insertYT : finish insert");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzz","insertYT : co loi them du lieu");
        }
    }

    public List<YeuThich> get_YT_theo_UserName(String user) throws SQLException {

        List<YeuThich> list = new ArrayList<>();

        try {
            if (this.connection != null){
                String sql = "SELECT * FROM yeuthich WHERE username = '" + user +"'";

                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){

                    YeuThich yt = new YeuThich();
                    yt.setId_yeuthich(resultSet.getInt("id_yeuthich"));
                    yt.setUsername(resultSet.getString("username"));
                    yt.setId_sp(resultSet.getInt("id_sp"));

                    list.add(yt);
                }

            }
        } catch (SQLException throwables) {
            Log.d("zzzz", "getThongBaoYT: lỗi truy vấn");
            throwables.printStackTrace();

        }

        return list;
    }
}
