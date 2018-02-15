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
import java.util.ArrayList;
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

    private boolean scheduler;

    @Autowired
    private QueryDSL queryDSL;

    private String language = "1";
    private static final Logger LOG = Logger.getLogger(ScheduledPinDistSale.class.getName());

    @Scheduled(fixedRate = 5000000)
    public void reportCurrentTime() throws InterruptedException {
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
        Thread.sleep(5000);
        try {
            LOG.info("Start PindisTSale in Web Services.");
            startPinDistSale();
            LOG.info("End PindisTSale in Web Services.");
        } catch (Exception ex) {
            LOG.warning("Exception Encontuered en Proccess PinDistale in Web Services. " + ex);
        }

    }

    private void startPinDistSale() throws InterruptedException {
        if (scheduler) {
            ExecutorService executor = Executors.newFixedThreadPool(200);
            PinDistSaleThread worker = null;
            for (int i = 0; i < 100; i++) {
                String productID = bagProduct(randInt(0, 7));
                String terminalID = bagTerminal(randInt(0, 10));
                worker = new PinDistSaleThread(language, "1", terminalID, "1234", productID, "0.1", "121", "" + i);
                executor.execute(worker);
                Thread.sleep(500);
            }
            executor.shutdown();
            executor.shutdownNow();
        }
    }

    private void startLogin(String language, String version, String user, String pass) {
        if (scheduler) {
            ExecutorService executor = Executors.newFixedThreadPool(4);
            LoginThread worker = null;
            worker = new LoginThread(language, version, user, pass);
            executor.execute(worker);
            executor.shutdown();
            executor.shutdownNow();

        }
    }

    private String bagTerminal(int i) {
        String terminal;
        List<String> terminalList = new ArrayList();
        terminalList.add("8866367");
        terminalList.add("8325985");
        terminalList.add("8533257");
        terminalList.add("2188494");
        terminalList.add("2237911");
        terminalList.add("3063199");
        terminalList.add("4071966");
        terminalList.add("9242953");
        terminalList.add("2590651");
        terminalList.add("8503767");
        terminal = terminalList.get(i);
        return terminal;
    }

    private String bagProduct(int i) {
        String product;
        List<String> productList = new ArrayList();
        productList.add("170");
        productList.add("171");
        productList.add("174");
        productList.add("1274");
        productList.add("101170");
        productList.add("101174");
        productList.add("8000000");
        product = productList.get(i);
        return product;
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
