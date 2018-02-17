/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.scheduledtasks;

import com.querydsl.core.Tuple;
import com.virus.threads.LoginThread;
import com.virus.constant.ViewConstant;
import com.virus.controller.PinDistSaleThread;
import com.virus.repository.QueryDSL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author krodriguez
 */
@Component
public class ScheduledPinDistSale {

    @Autowired
    private QueryDSL queryDSL;

    private boolean scheduler;
    private String language = "1";

    private static final Logger LOG = Logger.getLogger(ScheduledPinDistSale.class.getName());

    @Scheduled(fixedRate = 300000000)
    public void reportCurrentTime() throws InterruptedException {

        loginWebServices();
        Thread.sleep(10000);
        pindDistSaleWebServices();
    }

    private void loginWebServices() {
        if (scheduler) {
            List<Tuple> listUser = queryDSL.getUser2LoginMethodWebServices();
            try {
                LOG.info("Start Login Terminals in Web Services.");
                for (Tuple item : listUser) {
                    Object oLan = item.toArray()[0];
                    Object oVer = item.toArray()[1];
                    Object oUser = item.toArray()[2];
                    Object oPass = item.toArray()[3];
                    String lan = String.valueOf(oLan);
                    String ver = String.valueOf(oVer);
                    String user = String.valueOf(oUser);
                    String pass = String.valueOf(oPass);
                    startLogin(lan, ver, user, pass);
                }
                LOG.info("Finish Login Terminals Success in Web Services.");
            } catch (Exception ex) {
                LOG.warning("Exception Encontuered en Login Terminals in Web Services. " + ex);
            }
        }
    }

    private void pindDistSaleWebServices() {
        if (scheduler) {
            try {
                LOG.info("Start PindisTSale in Web Services.");
                startPinDistSale();
                LOG.info("End PindisTSale in Web Services.");
            } catch (Exception ex) {
                LOG.warning("Exception Encontuered en Proccess PinDistale in Web Services. " + ex);
            }
        }
    }

    private void startLogin(String language, String version, String user, String pass) {
        if (scheduler) {
            ExecutorService executor = Executors.newFixedThreadPool(99);
            LoginThread worker = null;
            worker = new LoginThread(language, version, user, pass);
            executor.execute(worker);
            executor.shutdown();
            executor.shutdownNow();
        }
    }

    private void startPinDistSale() throws InterruptedException {
        if (scheduler) {
            ExecutorService executor = Executors.newFixedThreadPool(99);
            PinDistSaleThread worker = null;
            int invoice = randInt(100, 200);
            for (int i = 0; i < 300; i++) {
                invoice++;
                String productID = getProduct();
                String terminalID = getTerminal();
                worker = new PinDistSaleThread(language, "1", terminalID, "1234", productID, "1", "1234567", "" + invoice);
                executor.execute(worker);
                Thread.sleep(250);
            }
            executor.shutdown();
            executor.shutdownNow();
        }
    }

    private String getTerminal() {
        int countTerminals = queryDSL.getCountTerminals();
        int maxTerminal = countTerminals + 1;
        String terminalID = queryDSL.getTerminal2PindistSaleMethodWebServices(randInt(1, maxTerminal));
        return terminalID;
    }

    private String getProduct() {
        int countProducts = queryDSL.getCountProducts();
        int maxProduct = countProducts + 1;
        String productID = queryDSL.getProduct2PindistSaleMethodWebServices(randInt(1, maxProduct));
        return productID;
    }

    private int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min)) + min;
        return randomNum;
    }

    @Value("${scheduler}")
    public void setImageFolder(boolean scheduler) {
        this.scheduler = scheduler;
        ViewConstant.SCHEDULER_METHOD = scheduler;
    }

}
