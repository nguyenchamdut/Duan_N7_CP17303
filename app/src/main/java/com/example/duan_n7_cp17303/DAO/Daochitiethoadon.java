package com.example.duan_n7_cp17303.DAO;

import android.util.Log;

import com.example.duan_n7_cp17303.DTO.Chitiethoadon;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Daochitiethoadon {
    Connection connection;

    public Daochitiethoadon() {
        DbSqlServer db = new DbSqlServer();
        connection = db.openConnect();
    }
    public List<Chitiethoadon> getAll(){
        List<Chitiethoadon> list = new ArrayList<>();
        try {
            if (this.connection != null) {
                String sqlSanPham = "select * from chitiethoadon";
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSanPham);
                while (resultSet.next()) {
                    Chitiethoadon chitiethoadon = new Chitiethoadon();
                    chitiethoadon.setId_donhang(resultSet.getInt("id_donhang"));
                    chitiethoadon.setId_sp(resultSet.getInt("id_sp"));
                    chitiethoadon.setSoluong(resultSet.getInt("soluong"));
                    chitiethoadon.setGiamua(resultSet.getInt("giamua"));

                    list.add(chitiethoadon);
                }
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return list;
    }
    public void insertCTHD(Chitiethoadon cthd){
        try{
            if (this.connection != null){
                String insert = "insert into chitiethoadon(id_donhang,id_sp,soluong,giamua) values (" + cthd.getId_donhang() +"," + cthd.getId_sp() + ","+ cthd.getSoluong() + ","+cthd.getGiamua() +")";


                PreparedStatement statement = this.connection.prepareStatement(insert);
                statement.execute();
                Log.e("zzzzz","insertCTHD : finish insert");

            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzz","insertCTHD : co loi them du lieu");
        }
    }

    public Chitiethoadon getAll_CT_theo_id(int id){
        List<Chitiethoadon> list_CT = new ArrayList<>();

        try {
            if (this.connection != null){
                String sql = "SELECT * FROM chitiethoadon WHERE id_donhang = " + id;

                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){

                    Chitiethoadon chitiethoadon = new Chitiethoadon();
                    chitiethoadon.setId_donhang(resultSet.getInt("id_donhang"));
                    chitiethoadon.setId_sp(resultSet.getInt("id_sp"));
                    chitiethoadon.setSoluong(resultSet.getInt("soluong"));
                    chitiethoadon.setGiamua(resultSet.getInt("giamua"));

                    list_CT.add(chitiethoadon);
                }

            }
        } catch (SQLException throwables) {
            Log.d("TAG", "getThongBao: lỗi truy vấn");
            throwables.printStackTrace();

        }

        return list_CT.get(0);
    }
}
