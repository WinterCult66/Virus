/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.controller;

import com.emida.selenium.SeleniumRecordedTest;
import com.google.gson.Gson;
import com.querydsl.core.Tuple;
import com.virus.constant.ViewConstant;
import static com.virus.util.Util.listFolder;
import static com.virus.util.Util.folderNumberAleatory;
import com.virus.repository.QueryDSL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author krodriguez
 */
@RestController
@RequestMapping("/records")
public class RecordedTestController {

    @Autowired
    private QueryDSL queryDSL;

    private static final Log LOG = LogFactory.getLog(ContactController.class);

    public SeleniumRecordedTest seleniumRecordedTest = new SeleniumRecordedTest();

    @GetMapping("/showrecorded")
    public ModelAndView showRecords() {
        LOG.info("Enter to Method to Show Test Recorded");
        ModelAndView mav = new ModelAndView(ViewConstant.SHOWRECORDS);
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Tuple> query = queryDSL.getResultByUser(user.getUsername());
            String json = new Gson().toJson(query);
            mav.addObject("kevin", json);
        } catch (Exception ex) {
            LOG.error("ERROR IN SHOWRECORDED " + ex);
        }
        return mav;

    }

    @RequestMapping("/processrecords/{id}")
    public Map processRecords(@PathVariable String id) {
        LOG.info("Enter to Method to Proccess Test Recorded");
        Map<String, Object> responseImg = new LinkedHashMap();
        try {
            String fromMethodFolder = (folderNumberAleatory());
            List<Tuple> query = queryDSL.getResultByKey(id);
            seleniumRecordedTest.ReadRecordeds(query, fromMethodFolder, ViewConstant.SELENIUM_FOLDER);
            List<String> a = listFolder(fromMethodFolder);
            responseImg.put("gs", a.toString());
            System.out.println(a.toString());

        } catch (Exception ex) {
            LOG.error("ERROR IN PROCCESS RECORDS " + ex);
        }

        return responseImg;
    }

}
