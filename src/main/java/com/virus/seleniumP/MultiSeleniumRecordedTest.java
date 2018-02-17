/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.seleniumP;

import com.emida.util.Util;
import com.querydsl.core.Tuple;
import com.virus.repository.HistoryItemDetailRepository;
import com.virus.util.SaveDetailMultiBrowser;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

//This class and pacckage is provisional because the connection DB It is not modularized.// 
/**
 *
 * @author krodriguez
 */
public class MultiSeleniumRecordedTest implements Runnable {

    private static final Logger LOG = Logger.getLogger(MultiSeleniumRecordedTest.class.getName());
    //RemoteWebDriver driver = null;

    DesiredCapabilities capabilities = null;
    String driverName;
    String hubURL;
    List<Tuple> listRecorded;
    String folderImage;
    List listOptions;
    List<Object> objectList = new ArrayList<Object>();
    String startTime, endTime;
    JSONObject jsonObjectInformation = new JSONObject();
    JSONArray jsonObjectInfo2Array = new JSONArray();
    String uniqueIDGroup = UUID.randomUUID().toString();
    String uniqueID = UUID.randomUUID().toString();
    String folder;
    int count;
    WebDriver driver = null;
    HistoryItemDetailRepository historyItemDetailRepository;
    SaveDetailMultiBrowser saveDetailMultiBrowser = new SaveDetailMultiBrowser();

    @Override
    public void run() {
        try {
            try {
                Thread.currentThread().setName("Thread-" + Util.getNameDriver(count));
                String threadName = Thread.currentThread().getName();
                LOG.log(Level.INFO, "Thread Start " + threadName);
                startTime = Util.getDate2StartThread();
                LOG.log(Level.INFO, threadName + " Start Time: " + startTime);
                jsonObjectInformation.put("uniqueid", uniqueID);
                jsonObjectInformation.put("uniqueidgroup", uniqueIDGroup);
                jsonObjectInformation.put("driver", driverName);
                jsonObjectInformation.put("startime", startTime);
                LOG.log(Level.INFO, "{0} Start to Read Records", threadName);
                ReadRecordeds();
                LOG.log(Level.FINE, "{0} End to Read Records", threadName);
                endTime = Util.getDate2StartThread();
                jsonObjectInformation.put("endtime", endTime);
                LOG.log(Level.INFO, "{0} End Time: {1}", new Object[]{threadName, endTime});
                jsonObjectInfo2Array.add(jsonObjectInformation);
            } catch (Exception ex) {
                LOG.log(Level.WARNING, "ERROR FROM RUN METHOD INTERN: {0}", ex);
            }
        } catch (Exception ex) {
            LOG.log(Level.WARNING, "ERROR FROM RUN METHOD EXTERN: {0}", ex);
        }
    }

    private void ReadRecordeds() throws MalformedURLException {

        driver = Util.evaluatorBrowser(driverName, folder);
        //driver = new RemoteWebDriver(new URL(hubURL), capabilities);
        driver.manage().window().maximize();
        for (Tuple item : listRecorded) {
            Object oXpath = item.toArray()[0];
            Object oOption = item.toArray()[1];
            Object oValue = item.toArray()[2];
            String xpath = String.valueOf(oXpath);
            String option = String.valueOf(oOption);
            String value = String.valueOf(oValue);
            captureEventInit(option, value, xpath, folderImage);
            objectList.add(option);
        }
        captureEventInit("6", "", "", "");
    }

    private void captureEventInit(String event, String target, String param, String folderImage) {
        String events = "";
        int status = 0;
        try {
            if (event.equalsIgnoreCase("1")) {
                events = "1). URL: " + target;
                driver.get(target);
                status = 1;
            } else if (event.equalsIgnoreCase("2")) {
                events = "2). Send Text in " + param + " Send: " + target;
                driver.findElement(By.xpath(param)).sendKeys(target);
                status = 1;
            } else if (event.equalsIgnoreCase("3")) {
                events = "3). Press Click: " + param;
                driver.findElement(By.xpath(param)).click();
                status = 1;
            } else if (event.equalsIgnoreCase("4")) {
                events = "4). Sleep Time " + target;
                int longest = Integer.parseInt(target);
                Thread.sleep(longest);
                status = 1;
            } else if (event.equalsIgnoreCase("5")) {
                events = "5). Take Photo: " + target;
                Util.takeScreenShot(driver, target, folderImage);
                status = 1;
            } else if (event.equalsIgnoreCase("6")) {
                events = "6). Close Test";
                driver.quit();
                status = 1;
            } else {
                status = 0;
            }
            saveDetailMultiBrowser.SaveDetailBrowser(events, status, uniqueID, historyItemDetailRepository);
        } catch (Exception ex) {
            LOG.log(Level.WARNING, "Error in captureEventInit : ", ex);
            saveDetailMultiBrowser.SaveDetailBrowser(events, 0, uniqueID, historyItemDetailRepository);
        }

//        try {
//            switch (event) {
//                case "1":
//                    //LOG.log(Level.INFO, "1). URL:  {0}", target);
//                    events = "1). URL: " + target;
//                    driver.get(target);
//                    status = true;
//                    saveDetailMultiBrowser.SaveDetailBrowser(events, uniqueID, historyItemDetailRepository);
//                    break;
//                case "2":
//                    //LOG.log(Level.INFO, "2). Xpath:  {0} SEND KEY : {1}", new Object[]{param, target});
//                    events = "2). Send Text in " + param + " Send: " + target;
//                    saveDetailMultiBrowser.SaveDetailBrowser(events, uniqueID, historyItemDetailRepository);
//                    driver.findElement(By.xpath(param)).sendKeys(target);
//                    break;
//                case "3":
//                    events = "3). Press Click: " + param;
//                    //LOG.log(Level.INFO, "3). Xpath:  {0} Click ", param);
//                    saveDetailMultiBrowser.SaveDetailBrowser(events, uniqueID, historyItemDetailRepository);
//                    driver.findElement(By.xpath(param)).click();
//                    break;
//                case "4":
//                    events = "4). Sleep Time " + target;
//                    //LOG.info("4). SLEEP");
//                    saveDetailMultiBrowser.SaveDetailBrowser(events, uniqueID, historyItemDetailRepository);
//                    int longest = Integer.parseInt(target);
//                    Thread.sleep(longest);
//                    break;
//                case "5":
//                    events = "5). Take Photo: " + target;
//                    //LOG.info("5). TAKE SCREEN ");
//                    saveDetailMultiBrowser.SaveDetailBrowser(events, uniqueID, historyItemDetailRepository);
//                    Util.takeScreenShot(driver, target, folderImage);
//                    break;
//                case "6":
//                    //LOG.info("6). CLOSE ");
//                    driver.quit();
//                    break;
//                case "":
//                    LOG.info("EMPTY");
//                    driver.quit();
//                    break;
//            }
//        } catch (Exception ex) {
//            LOG.log(Level.WARNING, "ERROR FROM CAPTURE EVENT METHOD ( ) : {0}", ex);
//            driver.quit();
//        }
    }

    public MultiSeleniumRecordedTest(String driverName, String hubURL,
            List<Tuple> listRecorded, String folderImage, List<Object> objectList,
            JSONArray jsonObjectInfo2Array, int count, String folder, HistoryItemDetailRepository historyItemDetailRepository) {
        this.driverName = driverName;
        this.hubURL = hubURL;
        this.listRecorded = listRecorded;
        this.folderImage = folderImage;
        this.objectList = objectList;
        this.jsonObjectInfo2Array = jsonObjectInfo2Array;
        this.count = count;
        this.folder = folder;
        this.historyItemDetailRepository = historyItemDetailRepository;
    }

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    public JSONArray getJsonObjectInfo2Array() {
        return jsonObjectInfo2Array;
    }

    public void setJsonObjectInfo2Array(JSONArray jsonObjectInfo2Array) {
        this.jsonObjectInfo2Array = jsonObjectInfo2Array;
    }

    public String getUniqueIDGroup() {
        return uniqueIDGroup;
    }

    public void setUniqueIDGroup(String uniqueIDGroup) {
        this.uniqueIDGroup = uniqueIDGroup;
    }

}
