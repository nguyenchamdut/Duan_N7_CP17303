package com.example.duan_n7_cp17303.DTO;

public class YeuThich {
    private int id_yeuthich;
    private String username;
    private int id_sp;

    public YeuThich() {
    }

    public YeuThich(int id_yeuthich, String username, int id_sp) {
        this.id_yeuthich = id_yeuthich;
        this.username = username;
        this.id_sp = id_sp;
    }

    public int getId_yeuthich() {
        return id_yeuthich;
    }

    public void setId_yeuthich(int id_yeuthich) {
        this.id_yeuthich = id_yeuthich;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId_sp() {
        return id_sp;
    }

    public void setId_sp(int id_sp) {
        this.id_sp = id_sp;
    }
}
