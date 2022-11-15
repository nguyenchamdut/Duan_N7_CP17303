package com.example.duan_n7_cp17303.DTO;

public class Sanpham {
    int id_sp;
    int id_hangsp;
    String tensp;

    public Sanpham() {
    }

    public Sanpham(int id_sp, int id_hangsp, String tensp) {
        this.id_sp = id_sp;
        this.id_hangsp = id_hangsp;
        this.tensp = tensp;
    }

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public int getId_hangsp() {
        return id_hangsp;
    }

    public void setId_hangsp(int id_hangsp) {
        this.id_hangsp = id_hangsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }
}
