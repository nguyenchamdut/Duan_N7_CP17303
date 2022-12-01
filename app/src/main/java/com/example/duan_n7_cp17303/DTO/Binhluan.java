package com.example.duan_n7_cp17303.DTO;

public class Binhluan {
    int id_binhluan;
    int id_sp;
    String username;
    String textbinhluan;

    public Binhluan() {
    }

    public Binhluan(int id_binhluan, int id_sp, String username, String textbinhluan) {
        this.id_binhluan = id_binhluan;
        this.id_sp = id_sp;
        this.username = username;
        this.textbinhluan = textbinhluan;
    }

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTextbinhluan() {
        return textbinhluan;
    }

    public void setTextbinhluan(String textbinhluan) {
        this.textbinhluan = textbinhluan;
    }

    public int getId_binhluan() {
        return id_binhluan;
    }

    public void setId_binhluan(int id_binhluan) {
        this.id_binhluan = id_binhluan;
    }


}
