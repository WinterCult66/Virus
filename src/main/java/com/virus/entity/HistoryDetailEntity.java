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
@Entity(name = "historydetail")
public class HistoryDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String browser;
    private String starttime;
    private String endtime;
    private String groupkey;
    private String user;

    public HistoryDetailEntity(String browser, String starttime, String endtime, String groupkey, String user) {
        this.browser = browser;
        this.starttime = starttime;
        this.endtime = endtime;
        this.groupkey = groupkey;
        this.user = user;
    }

    public HistoryDetailEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getGroupkey() {
        return groupkey;
    }

    public void setGroupkey(String groupkey) {
        this.groupkey = groupkey;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "HistoryDetailEntity{" + "browser=" + browser + ", starttime=" + starttime + ", endtime=" + endtime + ", groupkey=" + groupkey + ", user=" + user + '}';
    }

}
