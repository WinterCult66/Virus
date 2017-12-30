package com.virus.pojos;

public class PojoDynamic {

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
        return "PojoDynamic{" + "optionselect=" + optionselect + ", divxpath=" + divxpath + ", valuetosend=" + valuetosend + '}';
    }

}
