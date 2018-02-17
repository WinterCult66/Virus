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
import com.virus.entity.QHistoryItemDetailEntity;
import com.virus.entity.QHistoryItemEntity;
import com.virus.entity.QLogin2MethodEntity;
import com.virus.entity.QProductsSales2WSEntity;
import com.virus.entity.QTerminalSales2WSEntity;
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
    private QTerminalSales2WSEntity qTerminalSales2WSEntity = QTerminalSales2WSEntity.terminalSales2WSEntity;
    private QProductsSales2WSEntity qProductsSales2WSEntity = QProductsSales2WSEntity.productsSales2WSEntity;
    private QHistoryItemEntity qHistoryItemEntity = QHistoryItemEntity.historyItemEntity;
    private QHistoryItemDetailEntity qHistoryItemDetailEntity = QHistoryItemDetailEntity.historyItemDetailEntity;

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

    // Method to Get List Items
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

    public String getNameTest(String UUIID) {
        String nameTest = null;
        try {
            JPAQuery<QAutomationRecordedItemEntity> query = new JPAQuery<QAutomationRecordedItemEntity>(em);
            nameTest = query.select(qAutItem.nameitem)
                    .from(qAutItem)
                    .where(qAutItem.keyari
                            .eq(UUIID)).fetchOne();
            LOG.info("Execute Query Select getNameTest Success");
        } catch (Exception ex) {
            LOG.error("Error Execute Query Select getNameTest " + ex);
        }
        return nameTest;
    }

    // Method to Get History Items
    public List<Tuple> getHistoryItem(String userName) {
        List<Tuple> listItemRecorded = null;
        try {
            JPAQuery<QHistoryItemEntity> query = new JPAQuery<QHistoryItemEntity>(em);
            listItemRecorded = query.select(qHistoryItemEntity.driver, qHistoryItemEntity.startime, qHistoryItemEntity.endtime,
                    qHistoryItemEntity.uniqueidgroup, qHistoryItemEntity.uniqueid, qHistoryItemEntity.nametest)
                    .from(qHistoryItemEntity)
                    .where(qHistoryItemEntity.user
                            .eq(userName))
                    .fetch();
            LOG.info("Execute Query Select getHistoryItem Success");
        } catch (Exception ex) {
            LOG.error("Error Execute Query Select  getHistoryItem Fail : " + ex);
        }
        return listItemRecorded;
    }

    // Method to Get History Items Details
    public List<Tuple> getHistoryItemDetail(String UUID) {
        List<Tuple> listItemRecorded = null;
        try {
            JPAQuery<QHistoryItemDetailEntity> query = new JPAQuery<QHistoryItemDetailEntity>(em);
            listItemRecorded = query.select(qHistoryItemDetailEntity.event, qHistoryItemDetailEntity.status)
                    .from(qHistoryItemDetailEntity)
                    .where(qHistoryItemDetailEntity.uniqueid
                            .eq(UUID))
                    .fetch();
            LOG.info("Execute Query Select getHistoryItemDetail Success");
        } catch (Exception ex) {
            LOG.error("Error Execute Query Select  getHistoryItemDetail Fail : " + ex);
        }
        return listItemRecorded;
    }

    // Method to Get List User to Login
    public List<Tuple> getUser2LoginMethodWebServices() {
        List<Tuple> listUsers2WS = null;
        try {
            JPAQuery<Login2MethodEntity> query = new JPAQuery<Login2MethodEntity>(em);
            listUsers2WS = query.select(qLogin2MethodEntity.lan, qLogin2MethodEntity.version, qLogin2MethodEntity.user,
                    qLogin2MethodEntity.pass)
                    .from(qLogin2MethodEntity).fetch();
            LOG.info("Execute Query Select getUser2LoginMethodWebServices Success");
        } catch (Exception ex) {
            LOG.error("Error Execute Query Select getUser2LoginMethodWebServices " + ex);
        }
        return listUsers2WS;

    }

    // Method to Get the count the Terminals
    public int getCountTerminals() {
        List<Tuple> listTerminal2WS = null;
        int countTerminals = 0;
        try {
            JPAQuery<QTerminalSales2WSEntity> query = new JPAQuery<QTerminalSales2WSEntity>(em);
            listTerminal2WS = query.select(qTerminalSales2WSEntity.terminalid, qTerminalSales2WSEntity.id)
                    .from(qTerminalSales2WSEntity)
                    .fetch();
            countTerminals = listTerminal2WS.size();
            LOG.info("Execute Query Select getCountTerminals Success");
        } catch (Exception ex) {
            LOG.error("Error Execute Query Select getCountTerminals " + ex);
        }
        return countTerminals;

    }

    // Method to Get Terminal By ID
    public String getTerminal2PindistSaleMethodWebServices(int id) {
        String terminal2WS = null;
        try {
            JPAQuery<QTerminalSales2WSEntity> query = new JPAQuery<QTerminalSales2WSEntity>(em);
            terminal2WS = query.select(qTerminalSales2WSEntity.terminalid)
                    .from(qTerminalSales2WSEntity)
                    .where(qTerminalSales2WSEntity.id
                            .eq(id)).fetchOne();
            LOG.info("Execute Query Select getUser2LoginMethodWebServices Success");
        } catch (Exception ex) {
            LOG.error("Error Execute Query Select getUser2LoginMethodWebServices " + ex);
        }
        return terminal2WS;
    }

    // Method to Get the count the Products
    public int getCountProducts() {
        List<Tuple> listTerminal2WS = null;
        int countProducts = 0;
        try {
            JPAQuery<QProductsSales2WSEntity> query = new JPAQuery<QProductsSales2WSEntity>(em);
            listTerminal2WS = query.select(qProductsSales2WSEntity.productid, qProductsSales2WSEntity.id)
                    .from(qProductsSales2WSEntity)
                    .fetch();
            countProducts = listTerminal2WS.size();
            LOG.info("Execute Query Select getCountProducts Success");
        } catch (Exception ex) {
            LOG.error("Error Execute Query Select getCountProducts " + ex);
        }
        return countProducts;

    }

    // Method to Get Products By ID
    public String getProduct2PindistSaleMethodWebServices(int id) {
        String terminal2WS = null;
        try {
            JPAQuery<QProductsSales2WSEntity> query = new JPAQuery<QProductsSales2WSEntity>(em);
            terminal2WS = query.select(qProductsSales2WSEntity.productid)
                    .from(qProductsSales2WSEntity)
                    .where(qProductsSales2WSEntity.id
                            .eq(id)).fetchOne();
            LOG.info("Execute Query Select getProduct2PindistSaleMethodWebServices Success");
        } catch (Exception ex) {
            LOG.error("Error Execute Query Select getProduct2PindistSaleMethodWebServices " + ex);
        }
        return terminal2WS;
    }

}
