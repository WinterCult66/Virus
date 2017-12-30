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
public class AutomationRecorderModel {

    private int id;
    private String optionselect;
    private String divxpath;
    private String valuetosend;
    private String categoryidnew;

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCategoryidnew() {
        return categoryidnew;
    }

    public void setCategoryidnew(String categoryidnew) {
        this.categoryidnew = categoryidnew;
    }

    

//    public AutomationRecorderModel(String optionselect, String divxpath, String valuetosend, String categoryidnew) {
//        this.optionselect = optionselect;
//        this.divxpath = divxpath;
//        this.valuetosend = valuetosend;
//        this.categoryidnew = categoryidnew;
//    }
//
//    public AutomationRecorderModel() {
//    }

    @Override
    public String toString() {
        return "AutomationRecorderModel{" + "id=" + id + ", optionselect=" + optionselect + ", divxpath=" + divxpath + ", valuetosend=" + valuetosend + '}';
    }

    
}
