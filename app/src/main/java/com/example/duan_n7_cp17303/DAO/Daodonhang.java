package com.example.duan_n7_cp17303.DAO;

import android.util.Log;

import com.example.duan_n7_cp17303.DTO.Donhang;
import com.example.duan_n7_cp17303.DTO.Taikhoan;
import com.example.duan_n7_cp17303.Sqlserver.DbSqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Daodonhang {
    Connection connection;

    public Daodonhang() {
        DbSqlServer db = new DbSqlServer();
        connection = db.openConnect();
    }
    public List<Donhang> getAll(){
        List<Donhang> list = new ArrayList<>();
        try{
            if (this.connection != null){
                String sqlSanPham = "select * from donhang";
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlSanPham);
                while (resultSet.next()){
                    Donhang donhang = new Donhang();
                    donhang.setId_donhang(resultSet.getInt("id_donhang"));
                    donhang.setId_khachhang(resultSet.getInt("id_khachhang"));
                    donhang.setNgay_muahang(resultSet.getDate("ngay_muahang"));
                    donhang.setTrangthai(resultSet.getString("trangthai"));
                    donhang.setTencuahang(resultSet.getString("tencuahang"));

                    list.add(donhang);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("zzzzz","getAllDH co loi");
        }
        return list;
    }

    public long insert_DonHang(Donhang donhang) throws SQLException {

        Statement statement = this.connection.createStatement();
        String sql = "insert into donhang values(" +donhang.getId_donhang() + "," +donhang.getId_khachhang() + ",'"+
                donhang.getNgay_muahang() +"',N'" + donhang.getTrangthai() + "','" + donhang.getTencuahang() +"')";

        if (statement.executeUpdate(sql)>0){
            return 1;
        }
        return -1;
    }
    public List<Donhang> getAll_DH_theo_idKH(int id){
        List<Donhang> list_dh = new ArrayList<>();

        try {
            if (this.connection != null){
                String sql = "SELECT * FROM donhang WHERE id_khachhang = " + id;

                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()){

                    Donhang donhang = new Donhang();
                    donhang.setId_donhang(resultSet.getInt("id_donhang"));
                    donhang.setId_khachhang(resultSet.getInt("id_khachhang"));
                    donhang.setNgay_muahang(resultSet.getDate("ngay_muahang"));
                    donhang.setTrangthai(resultSet.getString("trangthai"));
                    donhang.setTencuahang(resultSet.getString("tencuahang"));

                    list_dh.add(donhang);
                }

            }
        } catch (SQLException throwables) {
            Log.d("TAG", "getThongBao: lỗi truy vấn");
            throwables.printStackTrace();

        }

        return list_dh;
    }
    public void updateTrangThai(Donhang dh){
        try {
            if (this.connection != null){
                String sqlUpdate = "UPDATE donhang SET trangthai =  N'" + dh.getTrangthai() +"' where id_donhang = " + dh.getId_donhang();

                PreparedStatement statement = this.connection.prepareStatement(sqlUpdate);
                statement.execute();
                Log.e("zzzzz","updateTT : thanh cong");
            }
        }catch (Exception e){
            Log.e("zzzz","updateTT : co loi sua du lieu");
            e.printStackTrace();

        }
    }

    public int doanh_Thu(String tuNgay, String denNgay){

        List<Integer> list = new ArrayList<Integer>();

        try {

            String sql = "SELECT SUM(soluong * giamua) AS DOANHTHU FROM chitiethoadon" +
                    " JOIN donhang on donhang.id_donhang = chitiethoadon.id_donhang" +
                    " WHERE donhang.ngay_muahang BETWEEN '" + tuNgay + "'" + " AND '" + denNgay + "'";

            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                list.add(resultSet.getInt("DOANHTHU"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list.get(0);
    }


}
