package com.example.duan_n7_cp17303.DAO;

import android.util.Log;

import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Daotaikhoan {
    Connection objConn;
    public Daotaikhoan(){
        // hàm khởi tạo để mở kết nối
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public List<Taikhoan> getAll(){
        List<Taikhoan> listCat = new ArrayList<Taikhoan>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM taikhoan";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    Taikhoan objCat = new Taikhoan();
                    objCat.setUsername(resultSet.getString("username"));// truyền tên cột dữ liệu
                    objCat.setPass(resultSet.getString("pass")); // tên cột dữ liệu là pass

                    listCat.add(objCat);

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }
        return  listCat;
    }

    public boolean insertRow( Taikhoan objCat){
        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO taikhoan(username,pass,avatar) VALUES " +
                        "('"+objCat.getUsername() +"' ,  '" + objCat.getPass() + "' , '" + objCat.getAvatar() +"') ";

                PreparedStatement stmtInsert = this.objConn.prepareStatement(insertSQL);
                stmtInsert.execute();

                Log.d("zzzzz", "insertRow: finish insert");

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng

        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu " );
            e.printStackTrace();
        }
        return true;
    }

    public int check_login(String name, String pass) {

        List<Taikhoan> listCat = new ArrayList<Taikhoan>();

        try {
            if (this.objConn != null) {

                String sql = "SELECT * FROM taikhoan WHERE username = '" + name + "' AND pass = '" + pass + "'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sql); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    Taikhoan objCat = new Taikhoan();

                    objCat.setUsername(resultSet.getString("username"));// truyền tên cột dữ liệu
                    objCat.setPass(resultSet.getString("pass")); // tên cột dữ liệu là pass

                    listCat.add(objCat);

                }
            }

        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        if (listCat.size() == 0) {
            return -1;
        }

        return 1;
    }

    public Taikhoan get_SP_theo_User(String username) throws SQLException {

        List<Taikhoan> list_tk = new ArrayList<>();

        try {
            if (this.objConn != null){
                String sql = "SELECT * FROM taikhoan WHERE username = '" + username+ "'";

                Statement statement = this.objConn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){

                    Taikhoan objCat = new Taikhoan();
                    objCat.setUsername(resultSet.getString("username"));// truyền tên cột dữ liệu
                    objCat.setPass(resultSet.getString("pass")); // tên cột dữ liệu là pass
                    objCat.setAvatar(resultSet.getString("avatar"));
                    list_tk.add(objCat);
                }

            }
        } catch (SQLException throwables) {
            Log.d("TAG", "getThongBao: lỗi truy vấn");
            throwables.printStackTrace();

        }

        return list_tk.get(0);
    }


}

