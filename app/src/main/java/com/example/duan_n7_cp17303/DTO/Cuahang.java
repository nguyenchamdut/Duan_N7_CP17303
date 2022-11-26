package com.example.duan_n7_cp17303.DTO;

public class Cuahang {
    String tencuahang;
    String username;
    String email;
    int dienthoai;
    String diachi;

    public Cuahang() {
    }

    public Cuahang(String tencuahang, String username, String email, int dienthoai, String diachi) {
        this.tencuahang = tencuahang;
        this.username = username;
        this.email = email;
        this.dienthoai = dienthoai;
        this.diachi = diachi;
    }

    public String getTencuahang() {
        return tencuahang;
    }

    public void setTencuahang(String tencuahang) {
        this.tencuahang = tencuahang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(int dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
