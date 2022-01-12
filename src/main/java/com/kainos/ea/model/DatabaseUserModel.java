package com.kainos.ea.model;

public class DatabaseUserModel {
    private String username;
    private String passwordHash;
    private String salt;
    private boolean isAdmin;

    public DatabaseUserModel() {

    }
    public DatabaseUserModel(String username, String passwordHash, String salt, boolean isAdmin) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
