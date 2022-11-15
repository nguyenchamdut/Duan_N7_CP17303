package com.example.duan_n7_cp17303.DTO;

public class Hang {
    int id_hang;
    String tenhangsp;

    public Hang() {
    }

    public Hang(int id_hang, String tenhangsp) {
        this.id_hang = id_hang;
        this.tenhangsp = tenhangsp;
    }

    public int getId_hang() {
        return id_hang;
    }

    public void setId_hang(int id_hang) {
        this.id_hang = id_hang;
    }

    public String getTenhangsp() {
        return tenhangsp;
    }

    public void setTenhangsp(String tenhangsp) {
        this.tenhangsp = tenhangsp;
    }
}
