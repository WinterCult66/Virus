/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.virus.model.AjaxResponseBody;
import com.virus.pojos.HistoryPojo;
import com.virus.views.Views;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author krodriguez
 */
public class HistoryController {

    private static final Log LOG = LogFactory.getLog(HistoryController.class);

    public void InsertHistory(ArrayList<String[]> history) {
        HistoryPojo historyPojo = new HistoryPojo();

        for (int i = 0; i < history.size(); i++) {
            for (int j = 0; j < history.get(i).length; j++) {
                historyPojo.setStarttime(history.get(i)[0]);
                historyPojo.setDriver(history.get(i)[1]);
                historyPojo.setEndtime(history.get(i)[2]);
            }
        }

        System.out.println("TO String: " + historyPojo.getDriver());
    }
}
