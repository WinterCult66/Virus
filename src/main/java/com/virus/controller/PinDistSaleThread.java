/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.controller;

import com.emida.methods.FacadePinDistSale;
import com.virus.xmlutil.XmlFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author krodriguez
 */
public class PinDistSaleThread extends Thread {

    String language;
    String versionn;
    String terminalid;
    String clerkid;
    String productid;
    String amount;
    String account;
    String invoice;

    public PinDistSaleThread(String language, String versionn, String terminalid, String clerkid, String productid, String amount, String account, String invoice) {
        this.language = language;
        this.versionn = versionn;
        this.terminalid = terminalid;
        this.clerkid = clerkid;
        this.productid = productid;
        this.amount = amount;
        this.account = account;
        this.invoice = invoice;
    }
    
    

    @Override
    public void run() {
        String url = "http://192.168.2.44:82/soap/servlet/rpcrouter";
        System.out.println("### " + language + " ###" + versionn
                + " ###" + terminalid + " ###" + clerkid + " ###" + productid
                + " ###" + amount + " ###" + account + " ###" + invoice);

        XmlFormatter formatter = new XmlFormatter();
        FacadePinDistSale facadePinDistSale = new FacadePinDistSale();
        String result = facadePinDistSale.pinDistSale(url, versionn, terminalid, clerkid, productid, account, amount, invoice, language);
        String xmlresponse = formatter.format(result);
        Map<String, Object> response = new LinkedHashMap();
        response.put("success", true);
        response.put("data", xmlresponse);
        System.out.println("Response: ["+response+"] invoice ["+invoice+"]");

    }

}
