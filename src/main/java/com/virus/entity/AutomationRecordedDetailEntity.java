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
import javax.validation.constraints.NotNull;

/**
 *
 * @author krodriguez
 */
@Entity(name = "automationrecordeddetail")
public class AutomationRecordedDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Integer id;
    
    private String optionselect;
    
    private String divxpath;
    
    private String valuetosend;
    
    private String keyitemari;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOptionselect() {
        return optionselect;
    }

    public void setOptionselect(String optionselect) {
        this.optionselect = optionselect;
    }

    public String getDivxpath() {
        return divxpath;
    }

    public void setDivxpath(String divxpath) {
        this.divxpath = divxpath;
    }

    public String getValuetosend() {
        return valuetosend;
    }

    public void setValuetosend(String valuetosend) {
        this.valuetosend = valuetosend;
    }

    public String getKeyitemari() {
        return keyitemari;
    }

    public void setKeyitemari(String keyitemari) {
        this.keyitemari = keyitemari;
    }

    public AutomationRecordedDetailEntity(String optionselect, String divxpath, String valuetosend, String keyitemari) {
        this.optionselect = optionselect;
        this.divxpath = divxpath;
        this.valuetosend = valuetosend;
        this.keyitemari = keyitemari;
    }

    public AutomationRecordedDetailEntity() {
    }

}
