package com.example.duan_n7_cp17303.DTO;

public class Loai {
    int id_loai;
    String tenloai;

    public Loai() {
    }

    public Loai(int id_loai, String tenloai) {
        this.id_loai = id_loai;
        this.tenloai = tenloai;
    }

    public int getId_loai() {
        return id_loai;
    }

    public void setId_loai(int id_loai) {
        this.id_loai = id_loai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
