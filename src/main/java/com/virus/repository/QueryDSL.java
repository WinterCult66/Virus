/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.virus.entity.AutomationRecordedDetailEntity;
import com.virus.entity.Login2MethodEntity;
import com.virus.entity.QAutomationRecordedDetailEntity;
import com.virus.entity.QAutomationRecordedItemEntity;
import com.virus.entity.QLogin2MethodEntity;
import com.virus.entity.QUserr;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private QLogin2MethodEntity qLogin2MethodEntity = QLogin2MethodEntity.login2MethodEntity;
    private static final Log LOG = LogFactory.getLog(QueryDSL.class);

    @PersistenceContext
    private EntityManager em;

    public void findByUser(String user) {

    }

    public List<Tuple> getResultByKey(String key) {

        List<Tuple> listDetailRecorded = null;
        try {
            JPAQuery<AutomationRecordedDetailEntity> query = new JPAQuery<AutomationRecordedDetailEntity>(em);
            listDetailRecorded = query.select(qAutDetail.divxpath, qAutDetail.optionselect, qAutDetail.valuetosend)
                    .from(qAutDetail)
                    .where(qAutDetail.keyitemari
                            .eq(key))
                    .fetch();
            LOG.info("Execute Query Select {0} getResultByKey");
        } catch (Exception ex) {
            LOG.error("Error Execute Query Select {0} getResultByKey" + ex);
        }
        return listDetailRecorded;
    }

    public List<Tuple> getResultByUser(String userName) {
        List<Tuple> listItemRecorded = null;
        try {
            JPAQuery<QAutomationRecordedItemEntity> query = new JPAQuery<QAutomationRecordedItemEntity>(em);
            listItemRecorded = query.select(qAutItem.descriptionitem, qAutItem.nameitem, qAutItem.keyari)
                    .from(qAutItem)
                    .where(qAutItem.user
                            .eq(userName))
                    .fetch();
            LOG.info("Execute Query Select {0} getResultByUser");
        } catch (Exception ex) {
            LOG.error("Error Execute Query Select {0} getResultByUser" + ex);
        }
        return listItemRecorded;
    }

    public List<Tuple> getUser2LoginMethodWebServices() {
        List<Tuple> listUsers2WS = null;
        try {
            JPAQuery<Login2MethodEntity> query = new JPAQuery<Login2MethodEntity>(em);
            listUsers2WS = query.select(qLogin2MethodEntity.lan, qLogin2MethodEntity.version, qLogin2MethodEntity.user,
                     qLogin2MethodEntity.pass)
                    .from(qLogin2MethodEntity).fetch();
            LOG.info("Execute Query Select getUser2LoginMethodWebServices Success" );
        } catch (Exception ex) {
            LOG.error("Error Execute Query Select getUser2LoginMethodWebServices " + ex);
        }
        return listUsers2WS;
        
    }

}
