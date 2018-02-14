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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000000)
    public void reportCurrentTime() {
        List<Tuple> listUser = queryDSL.getUser2LoginMethodWebServices();
        System.out.println(listUser + "Cantidad" + listUser.size());
        
//        
//       for (Tuple item : listUser) {
//           item
//        }
        // startLogin();
        //startPinDistSale();

    }

    private void startPinDistSale() {
        if (scheduler) {
            ExecutorService executor = Executors.newFixedThreadPool(100);
            PinDistSaleThread worker = null;
            for (int i = 0; i < 50; i++) {
                String productID = bagProduct(randInt(0, 3));
                String terminalID = bagTerminal(randInt(0, 3));
                worker = new PinDistSaleThread(language, "121", terminalID, "121", productID, "121", "121", "121" + i);
                executor.execute(worker);
            }
            executor.shutdown();
            executor.shutdownNow();
        }
    }

    private void startLogin() {
        if (scheduler) {
            ExecutorService executor = Executors.newFixedThreadPool(4);
            LoginThread worker = null;
            for (int i = 0; i < 4; i++) {
                worker = new LoginThread(language, "1", "kevin", "1234");
                executor.execute(worker);
            }
            executor.shutdown();
            executor.shutdownNow();

        }
    }

    private String bagTerminal(int i) {
        String terminal;
        List<String> terminalList = new ArrayList();
        terminalList.add("kevin");
        terminalList.add("alexander");
        terminalList.add("rodriguez");
        terminal = terminalList.get(i);
        return terminal;
    }

    private String bagProduct(int i) {
        String product;
        List<String> productList = new ArrayList();
        productList.add("allison");
        productList.add("test");
        productList.add("2018");
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
