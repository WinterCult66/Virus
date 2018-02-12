/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.scheduledtasks;

import com.virus.constant.ViewConstant;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        if (scheduler) {
            System.out.println("KEVIN RODRIGUEZ SCH " + dateFormat.format(new Date()));
        }
    }

    @Value("${scheduler}")
    public void setImageFolder(boolean scheduler) {
        this.scheduler = scheduler;
        ViewConstant.SCHEDULER_METHOD = scheduler;
    }

}
