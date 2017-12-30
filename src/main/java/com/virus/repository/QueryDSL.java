/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.virus.entity.AutomationRecordedDetailEntity;
import com.virus.entity.QAutomationRecordedDetailEntity;
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

    private QAutomationRecordedDetailEntity qAutomationRecordedDetailEntity = QAutomationRecordedDetailEntity.automationRecordedDetailEntity;

    @PersistenceContext
    private EntityManager entityManager;

    public void find() {
        JPAQuery<AutomationRecordedDetailEntity> query = new JPAQuery<AutomationRecordedDetailEntity>(entityManager);
       
       
        System.out.println(query.select(qAutomationRecordedDetailEntity).from(qAutomationRecordedDetailEntity));
    }
}
