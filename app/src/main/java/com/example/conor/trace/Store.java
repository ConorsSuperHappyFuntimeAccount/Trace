package com.example.conor.trace;


import android.app.Application;

public class Store extends Application {

    private String email;
    private String password;
    private double score;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        System.out.println("1");this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
