/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.model;

/**
 *
 * @author Kevin
 */
public class DoitModel {

    private long id;

    private String email;

    private String name;

    private String categoryidnew;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryidnew() {
        return categoryidnew;
    }

    public void setCategoryidnew(String categoryidnew) {
        this.categoryidnew = categoryidnew;
    }

    public DoitModel(long id, String email, String name, String categoryidnew) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.categoryidnew = categoryidnew;
    }

    public DoitModel() {
    }

    @Override
    public String toString() {
        return "DoitModel{" + "id=" + id + ", email=" + email + ", name=" + name + ", categoryidnew=" + categoryidnew + '}';
    }

}
