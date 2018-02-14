/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author krodriguez
 */
@Entity(name = "loginmethod")
public class Login2MethodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String lan;
    private String version;
    private String user;
    private String pass;

    public Login2MethodEntity(String lan, String version, String user, String pass) {
        this.lan = lan;
        this.version = version;
        this.user = user;
        this.pass = pass;
    }

    public String getLanguage() {
        return lan;
    }

    public void setLanguage(String language) {
        this.lan = language;
    }

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
}
