package com.virus.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author krodriguez
 */
@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "userNamse")
    private String userNamse;

    @Column(name = "detail")
    private String detail;

    @Column(name = "url")
    private String url;

    public Log(int id, Date date, String userNamse, String detail, String url) {
        this.id = id;
        this.date = date;
        this.userNamse = userNamse;
        this.detail = detail;
        this.url = url;
    }

    public Log() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserNamse() {
        return userNamse;
    }

    public void setUserNamse(String userNamse) {
        this.userNamse = userNamse;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
