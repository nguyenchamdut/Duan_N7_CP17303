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
                    kh.setDienthoai(resultSet.getInt("dienthoai"));
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

    public List<Khachhang> checkttkhach(){
        List<Khachhang> listCat = new ArrayList<Khachhang>();

        try {
            if (this.connection != null) {

                String sqlQuery = "SELECT id_khachhang, username  FROM khachhang" ;

                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    Khachhang objCat = new Khachhang();
                    objCat.setId_khachhang(Integer.parseInt(resultSet.getString("id_khachhang")));// truyền tên cột dữ liệu
                    objCat.setUsername(resultSet.getString("username")); // tên cột dữ liệu là pass

                    Log.d("TAG", "checkttkhach: " + "username");
                    listCat.add(objCat);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }
        return  listCat;
    }


}
