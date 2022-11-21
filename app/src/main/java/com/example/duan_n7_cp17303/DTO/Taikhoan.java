package com.example.duan_n7_cp17303.DTO;

public class Taikhoan {
    String username;
    String pass;
    String avatar;

    public Taikhoan() {
    }

    public Taikhoan(String username, String pass, String avatar) {
        this.username = username;
        this.pass = pass;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
