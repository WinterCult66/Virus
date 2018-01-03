/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.controller;

import com.google.gson.Gson;
import com.querydsl.core.Tuple;
import com.virus.constant.ViewConstant;
import com.virus.repository.QueryDSL;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.collect.Lists;
import com.google.common.base.Functions;


/**
* @author krodriguez
 */
@Controller
@RequestMapping("/records")
public class RecordedTestController {

    @Autowired
    private QueryDSL queryDSL;

    private static final Log LOG = LogFactory.getLog(ContactController.class);

    @GetMapping("/showrecorded")
    public ModelAndView showRecords() {
        LOG.info("Enter to Method to Show Test Recorded");
        ModelAndView mav = new ModelAndView(ViewConstant.SHOWRECORDS);
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Tuple> query = queryDSL.getResultByUser(user.getUsername());
//            List<String> strings = Lists.transform(query, Functions.toStringFunction());
            String json = new Gson().toJson(query);
            System.out.println(json);
            mav.addObject("kevin", json);
        } catch (Exception ex) {
            LOG.error("ERROR IN SHOWRECORDED " + ex);
        }
        return mav;

    }

}
