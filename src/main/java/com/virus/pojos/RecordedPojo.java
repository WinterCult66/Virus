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
public class RecordedPojo {

    private String optionselect;
    private String divxpath;
    private String valuetosend;

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

    @Override
    public String toString() {
        return "RecordedPojo{" + "optionselect=" + optionselect + ", divxpath=" + divxpath + ", valuetosend=" + valuetosend + '}';
    }

}
