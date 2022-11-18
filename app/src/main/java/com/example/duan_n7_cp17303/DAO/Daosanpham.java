package com.example.duan_n7_cp17303.DAO;

import android.util.Log;

import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Daosanpham {
    Connection connection;
    public Daosanpham(){
        DbSqlServer db = new DbSqlServer();
        connection = db.openConnect();
    }

    public List<Sanpham> getAllQuan(){
        List<Sanpham> sanphamList = new ArrayList<>();

        try{
            if (this.connection != null){
                String sqlSanPham = "select * from sanpham where id_loai = '1'";
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSanPham);
                while (resultSet.next()){
                    Sanpham sp = new Sanpham();
                    sp.setId_sp(resultSet.getInt("id_sp"));
                    sp.setId_loai(resultSet.getInt("id_loai"));
                    sp.setTensp(resultSet.getString("tensp"));
                    sp.setGiatien(resultSet.getString("giatien"));
                    sp.setSoluong(resultSet.getInt("soluong"));
                    sp.setAnh(resultSet.getString("anh"));
                    sanphamList.add(sp);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzzz","getAllQuan co loi");
        }
        return sanphamList;
    }
    public List<Sanpham> getAllAo(){
        List<Sanpham> sanphamList = new ArrayList<>();

        try{
            if (this.connection != null){
                String sqlSanPham = "select * from sanpham where id_loai = '2'";
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSanPham);
                while (resultSet.next()){
                    Sanpham sp = new Sanpham();
                    sp.setId_sp(resultSet.getInt("id_sp"));
                    sp.setId_loai(resultSet.getInt("id_loai"));
                    sp.setTensp(resultSet.getString("tensp"));
                    sp.setGiatien(resultSet.getString("giatien"));
                    sp.setSoluong(resultSet.getInt("soluong"));
                    sp.setAnh(resultSet.getString("anh"));
                    sanphamList.add(sp);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzzz","getAllAo co loi");
        }
        return sanphamList;
    }
    public List<Sanpham> getAllKhac(){
        List<Sanpham> sanphamList = new ArrayList<>();

        try{
            if (this.connection != null){
                String sqlSanPham = "select * from sanpham where id_loai = '3'";
                Statement statement = this.connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sqlSanPham);
                while (resultSet.next()){
                    Sanpham sp = new Sanpham();
                    sp.setId_sp(resultSet.getInt("id_sp"));
                    sp.setId_loai(resultSet.getInt("id_loai"));
                    sp.setTensp(resultSet.getString("tensp"));
                    sp.setGiatien(resultSet.getString("giatien"));
                    sp.setSoluong(resultSet.getInt("soluong"));
                    sp.setAnh(resultSet.getString("anh"));
                    sanphamList.add(sp);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzzz","getAllKhac co loi");
        }
        return sanphamList;
    }
    public void insertSP(Sanpham sp){
        try{
            if (this.connection != null){
                String insertSP = "insert into sanpham(id_loai,tensp,giatien,soluong,anh) value (N'" +sp.getId_loai() + sp.getTensp() + sp.getGiatien() + sp.getSoluong() + sp.getAnh() +"')";

                String generatedColumns[] = {"id_sp"};

                PreparedStatement statement = this.connection.prepareStatement(insertSP,generatedColumns);
                statement.execute();
                Log.e("zzzzz","insert : finish insert");

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()){
                    long id = rs.getLong(1);
                    Log.e("zzzz","insert : id = "+ id);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzz","insert : co loi them du lieu");
        }
    }

}
