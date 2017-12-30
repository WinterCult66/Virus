package com.virus.model;

public class LoginMethodModel {

    private String version;

    private String user;

    private String password;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginMethodModel{" + "version=" + version + ", user=" + user + ", password=" + password + '}';
    }

    
}
