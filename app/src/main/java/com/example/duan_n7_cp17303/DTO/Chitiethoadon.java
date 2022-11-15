package com.example.duan_n7_cp17303.DTO;

public class Chitiethoadon {
    int id_donhang;
    int id_sp;
    int soluong;
    int giamua;

    public Chitiethoadon() {
    }

    public Chitiethoadon(int id_donhang, int id_sp, int soluong, int giamua) {
        this.id_donhang = id_donhang;
        this.id_sp = id_sp;
        this.soluong = soluong;
        this.giamua = giamua;
    }

    public int getId_donhang() {
        return id_donhang;
    }

    public void setId_donhang(int id_donhang) {
        this.id_donhang = id_donhang;
    }

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiamua() {
        return giamua;
    }

    public void setGiamua(int giamua) {
        this.giamua = giamua;
    }
}
