/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.controller;

import com.emida.selenium.SeleniumRecordedTest;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.querydsl.core.Tuple;
import com.virus.constant.ViewConstant;
import com.virus.entity.AutomationRecordedDetailEntity;
import com.virus.entity.AutomationRecordedItemEntity;
import com.virus.model.AjaxResponseBody;
import com.virus.pojos.RecordedPojo;
import com.virus.repository.AutomationRecordedDetailRepository;
import com.virus.repository.AutomationRecordedItemRepository;
import static com.virus.util.Util.listFolder;
import static com.virus.util.Util.folderNumberAleatory;
import com.virus.repository.QueryDSL;
import com.virus.services.AutomationRecordedItemService;
import com.virus.views.Views;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author krodriguez
 */
@Controller
@RestController
@RequestMapping("/records")
public class RecordedTestController {

    @Autowired
    private AutomationRecordedDetailRepository automationRecordedDetailRepository;

    @Autowired
    private AutomationRecordedItemRepository automationRecordedItemRepository;
    
    @Autowired
    private AutomationRecordedItemService automationRecordedItemService;

    @Autowired
    private QueryDSL queryDSL;

    private static final Log LOG = LogFactory.getLog(ContactController.class);

    public SeleniumRecordedTest seleniumRecordedTest = new SeleniumRecordedTest();

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/saverecords/{nameParam}//{descParam}")
    public AjaxResponseBody getValues(@RequestBody List<RecordedPojo> recordedPojo, @PathVariable String nameParam, @PathVariable String descParam) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOG.info("Data to Proccess " + recordedPojo.toString());
        String userId = "";
        LOG.info("Enter to Method to Save Test Recorded");
        String uniqueID = UUID.randomUUID().toString();
        RecordedPojo saveRecordedPojo = new RecordedPojo();
        for (int index = 0; index < recordedPojo.size(); index++) {
            saveRecordedPojo = recordedPojo.get(index);
            if (saveRecordedPojo != null) {
                try {
                    AutomationRecordedDetailEntity automationRecordedDetailEntity = null;
                    if (saveRecordedPojo.getOptionselect().equals("3")) {
                        automationRecordedDetailEntity = new AutomationRecordedDetailEntity(saveRecordedPojo.getOptionselect(),
                                saveRecordedPojo.getValuetosend(), saveRecordedPojo.getDivxpath(), uniqueID);
                    } else {
                        automationRecordedDetailEntity = new AutomationRecordedDetailEntity(saveRecordedPojo.getOptionselect(),
                                saveRecordedPojo.getDivxpath(), saveRecordedPojo.getValuetosend(), uniqueID);
                    }
                    automationRecordedDetailRepository.save(automationRecordedDetailEntity);
                    LOG.info("INSERT SUCCESS DETAIL");
                } catch (Exception ex) {
                    LOG.error("ERROR FAIL INSERT DETAIL + EXCEPTION {0}" + ex.toString());
                }
            }
        }
        try {
            AutomationRecordedItemEntity automationRecordedItemEntity = new AutomationRecordedItemEntity(nameParam, descParam, uniqueID, user.getUsername());
            automationRecordedItemRepository.save(automationRecordedItemEntity);
            LOG.info("INSERT SUCCESS ITEM");
        } catch (Exception ex) {
            LOG.error("ERROR FAIL INSERT ITEM + EXCEPTION {0}" + ex.toString());
        }
        return null;
    }

    @GetMapping("/showrecorded")
    public ModelAndView showRecords() {
        LOG.info("Enter to Method to Show Test Recorded");
        ModelAndView mav = new ModelAndView(ViewConstant.SHOWRECORDS);
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Tuple> query = queryDSL.getResultByUser(user.getUsername());
            String json = new Gson().toJson(query);
            mav.addObject("jsonList", json);
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

    @RequestMapping("/deleterecords/{key}")
    public Map deleteRecords(@PathVariable String key) {
        LOG.info("Enter to Method to Delete Records");
        Map<String, Object> responseImg = new LinkedHashMap();
        try {
            automationRecordedItemService.removeContact(key);           
            
        } catch (Exception ex) {
            LOG.error("ERROR IN PROCCESS RECORDS " + ex);
        }

        return responseImg;
    }

}
