/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author krodriguez
 */
@Entity(name = "automationrecordeditem")
public class AutomationRecordedItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nameitem;
    private String descriptionitem;
    private String keyari;
    private String user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameitem() {
        return nameitem;
    }

    public void setNameitem(String nameitem) {
        this.nameitem = nameitem;
    }

    public String getDescriptionitem() {
        return descriptionitem;
    }

    public void setDescriptionitem(String descriptionitem) {
        this.descriptionitem = descriptionitem;
    }

    public String getKeyari() {
        return keyari;
    }

    public void setKeyari(String keyari) {
        this.keyari = keyari;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public AutomationRecordedItemEntity(String nameitem, String descriptionitem, String keyari, String user) {
        this.nameitem = nameitem;
        this.descriptionitem = descriptionitem;
        this.keyari = keyari;
        this.user = user;
    }

    public AutomationRecordedItemEntity() {
    }

}
