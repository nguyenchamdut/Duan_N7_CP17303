package com.example.duan_n7_cp17303.DTO;

public class Binhluan {
    int id_binhluan;
    String binhluan;

    public Binhluan() {
    }

    public Binhluan(int id_binhluan, String binhluan) {
        this.id_binhluan = id_binhluan;
        this.binhluan = binhluan;
    }

    public int getId_binhluan() {
        return id_binhluan;
    }

    public void setId_binhluan(int id_binhluan) {
        this.id_binhluan = id_binhluan;
    }

    public String getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(String binhluan) {
        this.binhluan = binhluan;
    }
}
