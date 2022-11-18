package com.example.duan_n7_cp17303.DTO;

public class Thongbao {
    int id_thongbao;
    int id_sp;
    String tieude;
    String chitiettieude;

    public Thongbao() {
    }

    public Thongbao(int id_thongbao, int id_sp, String tieude, String chitiettieude) {
        this.id_thongbao = id_thongbao;
        this.id_sp = id_sp;
        this.tieude = tieude;
        this.chitiettieude = chitiettieude;
    }

    public int getId_thongbao() {
        return id_thongbao;
    }

    public void setId_thongbao(int id_thongbao) {
        this.id_thongbao = id_thongbao;
    }

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getChitiettieude() {
        return chitiettieude;
    }

    public void setChitiettieude(String chitiettieude) {
        this.chitiettieude = chitiettieude;
    }
}
