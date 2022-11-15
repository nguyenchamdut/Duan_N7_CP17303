package com.example.duan_n7_cp17303.DTO;

import java.sql.Date;

public class Donhang {
    int id_donhang;
    int id_khachhang;
    Date ngay_muahang;
    String trangthai;

    public Donhang() {
    }

    public Donhang(int id_donhang, int id_khachhang, Date ngay_muahang, String trangthai) {
        this.id_donhang = id_donhang;
        this.id_khachhang = id_khachhang;
        this.ngay_muahang = ngay_muahang;
        this.trangthai = trangthai;
    }

    public int getId_donhang() {
        return id_donhang;
    }

    public void setId_donhang(int id_donhang) {
        this.id_donhang = id_donhang;
    }

    public int getId_khachhang() {
        return id_khachhang;
    }

    public void setId_khachhang(int id_khachhang) {
        this.id_khachhang = id_khachhang;
    }

    public Date getNgay_muahang() {
        return ngay_muahang;
    }

    public void setNgay_muahang(Date ngay_muahang) {
        this.ngay_muahang = ngay_muahang;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
