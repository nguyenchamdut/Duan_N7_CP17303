package com.example.duan_n7_cp17303.DTO;

public class Sanpham {
    int id_sp;
    int id_loai;
    String tensp;
    String giatien;
    int soluong;
    String anh;

    public Sanpham() {
    }

    public Sanpham(int id_sp, int id_loai, String tensp, String giatien, int soluong,  String anh) {
        this.id_sp = id_sp;
        this.id_loai = id_loai;
        this.tensp = tensp;
        this.giatien = giatien;
        this.soluong = soluong;

        this.anh = anh;
    }

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public int getId_loai() {
        return id_loai;
    }

    public void setId_loai(int id_loai) {
        this.id_loai = id_loai;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGiatien() {
        return giatien;
    }

    public void setGiatien(String giatien) {
        this.giatien = giatien;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }



    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
