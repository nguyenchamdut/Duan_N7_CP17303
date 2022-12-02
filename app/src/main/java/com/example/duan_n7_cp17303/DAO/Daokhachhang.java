package com.example.duan_n7_cp17303.DAO;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.duan_n7_cp17303.Activity.ShowSPActivity;
import com.example.duan_n7_cp17303.DTO.Khachhang;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Daokhachhang {
    Connection connection;
    public Daokhachhang(){
        DbSqlServer db = new DbSqlServer();
        connection = db.openConnect();
    }

    public List<Khachhang> getAll(){
        List<Khachhang> khachhang = new ArrayList<>();
        try{
            if (this.connection != null){
                String sqlSanPham = "select * from khachhang";
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSanPham);
                while (resultSet.next()){
                    Khachhang kh = new Khachhang();
                    kh.setId_khachhang(resultSet.getInt("id_khachhang"));
                    kh.setUsername(resultSet.getString("username"));
                    kh.setHoten(resultSet.getString("hoten"));
                    kh.setEmail(resultSet.getString("email"));
                    kh.setDienthoai(resultSet.getString("dienthoai"));
                    kh.setDiachi(resultSet.getString("diachi"));
                    khachhang.add(kh);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzzz","getAll co loi");
        }
        return khachhang;
    }

    public void insertKh(Khachhang kh){
        try{
            if (this.connection != null){
                String insertKh = "insert into khachhang(username,hoten,email,dienthoai,diachi)" +
                        " values ('" +kh.getUsername() + "',N'"+kh.getHoten() + "','"+ kh.getEmail() +"', '"+ kh.getDienthoai()+"' , N'"+ kh.getDiachi() +"')";
                Log.d("cc1", "insertKh: " + insertKh);
                String generatedColumns[] = {"id_khachhang"};

                PreparedStatement statement = this.connection.prepareStatement(insertKh,generatedColumns);
                statement.execute();
                Log.e("zzzzz","insert : finish insert");

                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()){
                    long id = rs.getLong(1);
                    Log.e("zzzz","insert : id = "+ id);
                }
                Log.d("cc2", "insertKh: Thêm thành công");
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzz","insert : co loi them du lieu");
        }
    }

    public void updateRow(Khachhang khachhang){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE khachhang SET username = '" + khachhang.getUsername() + "', hoten = N'" + khachhang.getHoten() + "', email = '" + khachhang.getEmail() + "', dienthoai = '" + khachhang.getDienthoai() +"', diachi = N'" +khachhang.getDiachi() +"' where id_khachhang = " + khachhang.getId_khachhang();

                PreparedStatement stmt = this.connection.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu " );
            e.printStackTrace();
        }
    }
}
