/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.controller;

import com.emida.selenium.SeleniumRecordedTest;
import com.virus.seleniumP.MultiSeleniumRecordedTest;
import com.emida.util.Util;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.querydsl.core.Tuple;
import com.virus.constant.ViewConstant;
import com.virus.entity.AutomationRecordedDetailEntity;
import com.virus.entity.AutomationRecordedItemEntity;
import com.virus.entity.HistoryItemEntity;
import com.virus.model.AjaxResponseBody;
import com.virus.model.TokenModel;
import com.virus.pojos.RecordedPojo;
import com.virus.repository.AutomationRecordedDetailRepository;
import com.virus.repository.AutomationRecordedItemRepository;
import com.virus.repository.HistoryItemDetailRepository;
import com.virus.repository.HistoryItemRepository;
import static com.virus.util.Util.listFolder;
import static com.virus.util.Util.folderNumberAleatory;
import com.virus.repository.QueryDSL;
import com.virus.services.AutomationRecordedItemService;
import com.virus.services.AutomationRecorderDetailService;
import com.virus.views.Views;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private String chrome;
    private String edge;
    private String firefox;
    private String imageFolder;

    @Autowired
    private AutomationRecordedDetailRepository automationRecordedDetailRepository;

    @Autowired
    private AutomationRecordedItemRepository automationRecordedItemRepository;

    @Autowired
    private AutomationRecordedItemService automationRecordedItemService;

    @Autowired
    private AutomationRecorderDetailService automationRecorderDetailService;

    @Autowired
    private HistoryItemRepository historyItemRepository;

    @Autowired
    private QueryDSL queryDSL;

    @Autowired
    private HistoryItemDetailRepository historyItemDetailRepository;

    private static final Log LOG = LogFactory.getLog(RecordedTestController.class);

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
                    LOG.info("Insert Success Detail {0}");
                } catch (Exception ex) {
                    LOG.error("Error Insert Detail {0} AjaxResponseBody" + ex.toString());
                }
            }
        }
        try {
            AutomationRecordedItemEntity automationRecordedItemEntity = new AutomationRecordedItemEntity(nameParam, descParam, uniqueID, user.getUsername());
            automationRecordedItemRepository.save(automationRecordedItemEntity);
            LOG.info("Insert Success Item");
        } catch (Exception ex) {
            LOG.error("Error Insert Item {0} AjaxResponseBody" + ex.toString());
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
            LOG.error("Error showRecords " + ex);
        }
        return mav;
    }

    @GetMapping("/showreports")
    public ModelAndView showReports() {
        LOG.info("Enter to Method to Show Reports Recorded");
        ModelAndView mav = new ModelAndView(ViewConstant.SHOWREPORTS);
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Tuple> query = queryDSL.getHistoryItem(user.getUsername());
            String json = new Gson().toJson(query);
            mav.addObject("jsonReport", json);

        } catch (Exception ex) {
            LOG.error("Error showreports " + ex);
        }
        return mav;
    }

    @RequestMapping("/showreportss/{id}")
    public Map showReportsDetail(@PathVariable String id) {
        LOG.info("Enter to Method to Show Reports Detail Recorded");
        Map<String, Object> detailHistory = new LinkedHashMap();
        try {
            List<Tuple> query = queryDSL.getHistoryItemDetail(id);
            String json = new Gson().toJson(query);
            detailHistory.put("history", json);
        } catch (Exception ex) {
            LOG.error("Error showreports " + ex);
        }
        return detailHistory;
    }

    @RequestMapping("/processrecords/{id}")
    public Map processRecords(@PathVariable String id) {
        LOG.info("Enter to Method to Proccess Test Recorded");
        Map<String, Object> responseImg = new LinkedHashMap();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enableImage = false;
        try {
            List<String> listFolders = new ArrayList<>();
            String fromMethodFolder = "";
            for (int i = 0; i < 2; i++) {
                fromMethodFolder = (folderNumberAleatory(i));
                listFolders.add(fromMethodFolder);
            }
            System.out.println(fromMethodFolder);
            List<Tuple> query = queryDSL.getResultByKey(id);
            List<Object> objectList = new ArrayList<Object>();
            MultiSeleniumRecordedTest worker = null;
            String driverName = null;
            String folderSelenium;
            JSONArray jsonInfo2Array = new JSONArray();
            try {
                ExecutorService executor = Executors.newFixedThreadPool(1);
                for (int i = 0; i < 1; i++) {
                    driverName = Util.getNameDriver(i);
                    folderSelenium = getFolderSelenium(driverName);
                    worker = new MultiSeleniumRecordedTest(driverName, "http://localhost:4215/wd/hub",
                            query, listFolders.get(i), objectList, jsonInfo2Array, i, folderSelenium, historyItemDetailRepository);
                    executor.execute(worker);
                }
                executor.shutdown();
                while (!executor.isTerminated()) {

                }

            } catch (Exception ex) {
                LOG.error("Error Proccess Thread {0}  processRecords " + ex);
            }
            try {

                objectList = worker.getObjectList();
                jsonInfo2Array = worker.getJsonObjectInfo2Array();
                String uniqueIDGroup = worker.getUniqueIDGroup();
                String stringJsonInfo = jsonInfo2Array.toString();
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<List<TokenModel>>() {
                }.getType();
                List<TokenModel> tokenList = gson.fromJson(stringJsonInfo, type);

                for (TokenModel tokenModel : tokenList) {
                    try {
                        String nameTest = queryDSL.getNameTest(id);
                        tokenModel.setUniqueidgroup(uniqueIDGroup);
                        HistoryItemEntity historyItemEntity = null;
                        historyItemEntity = new HistoryItemEntity(user.getUsername(), tokenModel.getUniqueid(), tokenModel.getUniqueidgroup(), tokenModel.getStartime(), tokenModel.getDriver(), tokenModel.endtime, nameTest);
                        historyItemRepository.save(historyItemEntity);
                        LOG.info("Insert in Table HistoryItem: " + historyItemEntity.toString());
                    } catch (Exception ex) {
                        LOG.error("Insert Fail historyItemEntity" + ex);
                    }
                }
                LOG.info("Verify if Take Image");
                for (Object str : objectList) {
                    Object option = "5";
                    if (str.equals(option)) {
                        enableImage = true;
                        LOG.info("Image = " + enableImage);
                    }
                }
            } catch (Exception ex) {
                LOG.error(ex);
            }
            List<String> a = listFolder(listFolders.get(0));
            List<String> b = listFolder(listFolders.get(1));
            responseImg.put("gs", a.toString());
            responseImg.put("es", b.toString());
            responseImg.put("image", enableImage);
        } catch (Exception ex) {
            LOG.error("Error Proccess Records {0}  processRecords " + ex);
        }
        return responseImg;
    }

    @RequestMapping("/deleterecords/{key}")
    public boolean deleteRecords(@PathVariable String key) {
        LOG.info("Enter to Method to Delete Records");
        boolean responseDelete = false;
        try {
            responseDelete = automationRecordedItemService.removeItem(key);
            LOG.info("Complete Delete Item {0}");
            automationRecorderDetailService.listdeleteAlldetails(key);
            LOG.info("Complete Delete Detail {1}");
        } catch (Exception ex) {
            LOG.error("Error Delete, DeleteRecords " + ex);
        }
        return responseDelete;
    }

//    private HashMap<String, String> addInfofromTuple(String event, String target, String param, String uniqueID) {
//        HashMap<String, String> map = new HashMap<String, String>();
//        String events;
//        try {
//            switch (event) {
//                case "1":
//                    events = "Send URL: " + target;
//                    map.put(uniqueID, events);
//                    break;
//                case "2":
//                    events = "Send Value: " + target;
//                    map.put(uniqueID, events);
//                    break;
//                case "3":
//                    events = "Click in: " + param;
//                    map.put(uniqueID, events);
//                    break;
//                case "4":
//                    events = "Sleep Time: " + target;
//                    map.put(uniqueID, events);
//                    break;
//                case "5":
//                    events = "Take photo: " + target;
//                    map.put(uniqueID, events);
//                    break;
//                case "":
//                    break;
//            }
//        } catch (Exception ex) {
//            LOG.error("Error " + ex);
//        }
//
//        return map;
//    }

    public static String getFolderSelenium(String folderSeleniumBrowser) {
        String folder = null;
        if ("Chrome".equalsIgnoreCase(folderSeleniumBrowser)) {
            folder = ViewConstant.SELENIUM_CHROME;
        } else if ("Explorer".equalsIgnoreCase(folderSeleniumBrowser)) {
            folder = ViewConstant.SELENIUM_EDGE;
        } else if ("Firefox".equalsIgnoreCase(folderSeleniumBrowser)) {
            folder = ViewConstant.SELENIUM_FIREFOX;
        }
        return folder;
    }

    @Value("${selenium.chrome}")
    public void setDriverChrome(String chrome) {
        this.chrome = chrome;
        ViewConstant.SELENIUM_CHROME = chrome;
    }

    @Value("${selenium.edge}")
    public void setDriverEdge(String edge) {
        this.edge = edge;
        ViewConstant.SELENIUM_EDGE = edge;
    }

    @Value("${selenium.firefox}")
    public void setDriverFirefox(String firefox) {
        this.firefox = firefox;
        ViewConstant.SELENIUM_FIREFOX = firefox;
    }

    @Value("${image.folder}")
    public void setImageFolder(String imgagefolder) {
        this.imageFolder = imgagefolder;
        ViewConstant.IMAGE_FOLDER = imageFolder;
    }

}
