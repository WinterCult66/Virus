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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    }

    public List<Tuple> getResultByKey(String key) {

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
        List<Tuple> listItemRecorded = query.select(qAutItem.descriptionitem, qAutItem.nameitem, qAutItem.keyari)
                .from(qAutItem)
                .where(qAutItem.user
                .eq(userName))
                .fetch();        
        return listItemRecorded;
    }
}
