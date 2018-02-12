/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author krodriguez
 */
@Entity(name = "historyitemdetail")
public class HistoryItemDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String event;
    private int status;
    private String uniqueid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HistoryItemDetailEntity{"+ "event=" + event + ", status=" + status + ", uniqueid=" + uniqueid + '}';
    }

    public HistoryItemDetailEntity(String event, int status, String uniqueid) {
        this.event = event;
        this.status = status;
        this.uniqueid = uniqueid;
    }
}
