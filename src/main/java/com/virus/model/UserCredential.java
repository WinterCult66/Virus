package com.virus.model;

//@author krodriguez
public class UserCredential {

    private String userName;
    private String password;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserCredential(String userName, String password, String id) {
        //super();
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    public UserCredential() {

    }

    @Override
    public String toString() {
        return "UserCredential{" + "userName=" + userName + ", password=" + password + ", id=" + id + '}';
    }
    
}
