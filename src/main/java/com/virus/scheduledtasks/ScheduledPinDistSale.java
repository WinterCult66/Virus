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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
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
    private int countTransactions = 0;
    private int quantityTransactions = 0;
    private String quantityTransactionss;
    private String timeBetweenTransactionss;
    private int timeBetweenTransactions = 0;
    private String url;

    private static final Logger LOG = Logger.getLogger(ScheduledPinDistSale.class.getName());

    @Scheduled(cron = "${fixed.rate}")
    // 90000 1.5 Minutes
    public void reportCurrentTime() throws InterruptedException {

        loginWebServices();
        Thread.sleep(10000);
        pindDistSaleWebServices();
        LOG.log(Level.INFO, "Finish All PinDistSale Count Transactions = {0}", countTransactions);
    }

    private void loginWebServices() {
        if (scheduler) {
            List<Tuple> listUser = queryDSL.getUser2LoginMethodWebServices();
            try {
                if (!listUser.isEmpty()) {
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
                } else {
                    LOG.info("List Empty to Login Terminals in WS.");
                }

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
            //url, version, user, password, language
            worker = new LoginThread(language, version, user, pass, url);
            executor.execute(worker);
            executor.shutdown();
            executor.shutdownNow();
        }
    }

    private void startPinDistSale() throws InterruptedException {
        if (scheduler) {
            ExecutorService executor = Executors.newFixedThreadPool(99);
            PinDistSaleThread worker = null;
            int invoice = randInt(1000, 100000);
            quantityTransactions = Integer.parseInt(quantityTransactionss);
            for (int i = 0; i < quantityTransactions; i++) {
                invoice++;
                int phone = randInt(1111111, 9999999);
                String phoneInt = String.valueOf(phone);
                String productID = getProduct();
                String terminalID = getTerminal();
                worker = new PinDistSaleThread(language, "1", terminalID, "1234", productID, "1", phoneInt, "" + invoice, url);
                executor.execute(worker);
                //Thread.sleep(100);
                //Thread.sleep(150);
                timeBetweenTransactions = Integer.parseInt(timeBetweenTransactionss);
                TimeUnit.SECONDS.sleep(timeBetweenTransactions);
                countTransactions++;
            }
            executor.shutdown();
            executor.shutdownNow();
        }
    }

    private String getTerminal() {
        String terminal = null;
        List<String> terminals = new ArrayList<>();
        List<Tuple> test = queryDSL.getTerminalsList();
        for (Tuple item : test) {
            Object oTerminal = item.toArray()[0];
            String terminalObject = String.valueOf(oTerminal);
            terminals.add(terminalObject);
        }
        int terminalInt = randInt(0, test.size());
        terminal = terminals.get(terminalInt);
        return terminal;
    }

    private String getProduct() {
        String product = null;
        List<String> products = new ArrayList<>();
        List<Tuple> test = queryDSL.getProductList();
        for (Tuple item : test) {
            Object oTerminal = item.toArray()[0];
            String terminalObject = String.valueOf(oTerminal);
            products.add(terminalObject);
        }
        int terminalInt = randInt(0, test.size());
        product = products.get(terminalInt);
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

    @Value("${quantity.transactions}")
    public void setQuantityTransactions(String quantityTransactionss) {
        this.quantityTransactionss = quantityTransactionss;
        ViewConstant.QUANTITY_TRANSACTIONS = quantityTransactionss;
    }

    @Value("${time.between.transactions}")
    public void setTimeBetweenTransactions(String timeBetweenTransactionss) {
        this.timeBetweenTransactionss = timeBetweenTransactionss;
        ViewConstant.TIME_BETWEEN_TRANSACTIONS = timeBetweenTransactionss;
    }

    @Value("${url.ws}")
    public void setUrlWS(String url) {
        this.url = url;
        ViewConstant.URL_WS = url;
    }

}
