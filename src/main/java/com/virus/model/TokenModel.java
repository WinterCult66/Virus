/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.model;

/**
 *
 * @author krodriguez
 */
public class TokenModel {

    public String uniqueid;
    public String uniqueidgroup;
    public String startime;
    public String driver;
    public String endtime;

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
    
    

}
