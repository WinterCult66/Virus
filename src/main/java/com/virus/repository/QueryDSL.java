/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.virus.entity.AutomationRecordedDetailEntity;
import com.virus.entity.QAutomationRecordedDetailEntity;
import com.virus.entity.QAutomationRecordedItemEntity;
import com.virus.entity.QUserr;
import com.virus.entity.Userr;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.minidev.json.JSONArray;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kevin
 */
@Repository
public class QueryDSL {

    private QUserr qUserr = QUserr.userr;
    private QAutomationRecordedDetailEntity qAutDetail = QAutomationRecordedDetailEntity.automationRecordedDetailEntity;
    private QAutomationRecordedItemEntity qAutItem = QAutomationRecordedItemEntity.automationRecordedItemEntity;

    @PersistenceContext
    private EntityManager em;

    public void findByUser(String user) {
        //JPAQuery<Userr> query = new JPAQuery<Userr>(em);
        //Tuple list =  query.select(qUserr.username,qUserr.password).from(qUserr).fetchOne();
        //System.out.println("list : " + list);    
        //Userr userr1 = query.select(qUserr).from(qUserr).where(qUserr.username.eq(user)).fetchOne();
        //System.out.println("1 : " +userr1);
        //List <Userr> listUser = query.select(qUserr).from(qUserr).where(qUserr.username.eq(user)).fetch();  
    }

    public List<Tuple> findByKey(String key) {

        JPAQuery<AutomationRecordedDetailEntity> query = new JPAQuery<AutomationRecordedDetailEntity>(em);
        List<Tuple> listDetailRecorded = query.select(qAutDetail.divxpath, qAutDetail.optionselect, qAutDetail.valuetosend)
                .from(qAutDetail)
                .where(qAutDetail.keyitemari
                        .eq(key))
                .fetch();
        return listDetailRecorded;
    }

    public List<Tuple> getResultByUser(String userName) {
        JPAQuery<QAutomationRecordedItemEntity> query = new JPAQuery<QAutomationRecordedItemEntity>(em);
        List<Tuple> listItemRecorded = query.select(qAutItem.descriptionitem, qAutItem.nameitem,qAutItem.keyari)
                .from(qAutItem)
                .where(qAutItem.user
                .eq(userName))
                .fetch();
        System.out.println("USER : " + listItemRecorded);
        return listItemRecorded;

    }
}
