package com.example.duan_n7_cp17303.DAO;

import android.util.Log;

import com.example.duan_n7_cp17303.DTO.Thongbao;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoThongBao {

    Connection connection;

    public DaoThongBao() {
        DbSqlServer db = new DbSqlServer();
        connection = db.openConnect();

    }

    public List<Thongbao> getThongBao(){
        List<Thongbao> list_tb = new ArrayList<>();

        try {
            if (this.connection != null){
                String sql = "SELECT * FROM thongbao ORDER BY id_thongbao DESC";

                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){

                    Thongbao thongBao = new Thongbao();
                    thongBao.setId_thongbao(resultSet.getInt("id_thongbao"));
                    thongBao.setId_sp(resultSet.getInt("id_sp"));
                    thongBao.setTieude(resultSet.getString("tieude"));
                    thongBao.setChitiettieude(resultSet.getString("chitiettieude"));

                    list_tb.add(thongBao);
                }

            }
        } catch (SQLException throwables) {
            Log.d("TAG", "getThongBao: lỗi truy vấn");
            throwables.printStackTrace();

        }

        return list_tb;
    }

    public int insert_thongBao(Thongbao thongBao) throws SQLException{

        Statement statement = connection.createStatement();
        String sql = "insert into thongbao values("+ thongBao.getId_sp()
                + ",N'" + thongBao.getTieude() + "', N'" + thongBao.getChitiettieude() +  "')";

        Log.d("vvvvv", "insert_thongBao: " + sql);

        if (statement.executeUpdate(sql) > 0){
            Log.d("vvvvv", "insert_thongBao: ");
            return 1;
        }

        return -1;
    }

    public int update_thongBao(Thongbao thongBao) throws SQLException{
        Statement statement = connection.createStatement();
        String sql = "UPDATE thongbao set tieude = " + thongBao.getTieude() + ", chitiettieude = " + thongBao.getChitiettieude()
                + "WHERE id_thongbao = " + thongBao.getId_thongbao();

        if (statement.executeUpdate(sql) > 0){
            return 1;
        }

        return -1;
    }

    public int delete_thongBao(Thongbao thongBao) throws SQLException{
        Statement statement = connection.createStatement();

        String sql = "DELETE FROM thongbao WHERE id_thongbao = " + thongBao.getId_thongbao();

        if (statement.executeUpdate(sql) > 0){
            return 1;
        }

        return -1;
    }



}
