/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.pojos;

/**
 *
 * @author krodriguez
 */
public class HistoryPojo  {
    
    private String driver;
    private String starttime;
    private String endtime;
    private String groupkey;
    private String keyhistory;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
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

    public String getKeyhistory() {
        return keyhistory;
    }

    public void setKeyhistory(String keyhistory) {
        this.keyhistory = keyhistory;
    }

    public HistoryPojo(String driver, String starttime, String endtime, String groupkey, String keyhistory) {
        this.driver = driver;
        this.starttime = starttime;
        this.endtime = endtime;
        this.groupkey = groupkey;
        this.keyhistory = keyhistory;
    }

    public HistoryPojo() {
    }   
}
