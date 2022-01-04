package com.kainos.ea.model;

public class Details {
    private String username;
    private String password;

    public Details(){}

    public Details(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean equals(Details a){
        return (this.username.equals(a.getUsername()) && this.password.equals(a.getPassword()));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
