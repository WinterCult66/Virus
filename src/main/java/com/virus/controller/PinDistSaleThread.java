/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.controller;

import com.emida.methods.FacadePinDistSale;
import com.virus.xmlutil.XmlFormatter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author krodriguez
 */
public class PinDistSaleThread implements Runnable {

    private static final Log LOG = LogFactory.getLog(PinDistSaleThread.class);
    String language;
    String versionn;
    String terminalid;
    String clerkid;
    String productid;
    String amount;
    String account;
    String invoice;
    String xmlresponse;
    String url;

    public PinDistSaleThread(String language, String versionn, String terminalid, String clerkid, String productid, String amount, String account, String invoice, String url) {
        this.language = language;
        this.versionn = versionn;
        this.terminalid = terminalid;
        this.clerkid = clerkid;
        this.productid = productid;
        this.amount = amount;
        this.account = account;
        this.invoice = invoice;
        this.url= url;
    }

    @Override
    public void run() {
        initPinDistSale();
    }

    @Override
    public String toString() {
        return "PinDistSaleThread{" + "language=" + language + ", versionn=" + versionn + ", terminalid=" + terminalid + ", clerkid=" + clerkid + ", productid=" + productid + ", amount=" + amount + ", account=" + account + ", invoice=" + invoice + '}';
    }

    public String getXmlresponse() {
        return xmlresponse;
    }

    public void setXmlresponse(String xmlresponse) {
        this.xmlresponse = xmlresponse;
    }

    public void initPinDistSale() {
        try {
            LOG.info("Start PinDistSale");
            //String url = "http://192.168.2.59:8080/soap/servlet/rpcrouter";
            System.out.println(toString());
            XmlFormatter formatter = new XmlFormatter();
            FacadePinDistSale facadePinDistSale = new FacadePinDistSale();
            String result = facadePinDistSale.pinDistSale(url, versionn, terminalid, clerkid, productid, account, amount, invoice, language);
            xmlresponse = formatter.format(result);
            LOG.info(xmlresponse);
            LOG.info("End PindistSale");
        } catch (Exception ex) {
            LOG.warn("Error initPinDistSale: " + ex);
        }
    }

}
