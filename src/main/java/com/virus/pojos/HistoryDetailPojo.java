/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.pojos;

import java.util.List;

/**
 *
 * @author krodriguez
 */
public class HistoryDetailPojo {

    private String event;
    private String uniqueid;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    @Override
    public String toString() {
        return "HistoryDetailPojo{" + "event=" + event + ", uniqueid=" + uniqueid + '}';
    }

    

}
