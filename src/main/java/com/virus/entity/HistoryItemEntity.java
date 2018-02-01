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
@Entity(name = "historyitem")
public class HistoryItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String user;
    private String uniqueid;
    private String uniqueidgroup;
    private String startime;
    private String driver;
    private String endtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getUniqueidgroup() {
        return uniqueidgroup;
    }

    public void setUniqueidgroup(String uniqueidgroup) {
        this.uniqueidgroup = uniqueidgroup;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public HistoryItemEntity(String user, String uniqueid, String uniqueidgroup, String startime, String driver, String endtime) {
        this.user = user;
        this.uniqueid = uniqueid;
        this.uniqueidgroup = uniqueidgroup;
        this.startime = startime;
        this.driver = driver;
        this.endtime = endtime;
    }

    public HistoryItemEntity() {
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", user=" + user + ", uniqueid=" + uniqueid + ", uniqueidgroup=" + uniqueidgroup + ", startime=" + startime + ", driver=" + driver + ", endtime=" + endtime + '}';
    }

}
