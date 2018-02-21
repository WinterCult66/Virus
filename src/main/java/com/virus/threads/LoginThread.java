/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.threads;

import com.virus.xmlutil.XmlFormatter;
import com.emida.methods.FacadeLogin2;

/**
 *
 * @author krodriguez
 */
public class LoginThread implements Runnable {

    String language;
    String version;
    String user;
    String password;
    String xmlresponse;
    String url;

    public LoginThread(String language, String version, String user, String password, String url) {
        this.language = language;
        this.version = version;
        this.user = user;
        this.password = password;
        this.url = url;
    }

    @Override
    public void run() {
        initLogin();
    }

    public void initLogin() {
        System.out.println(toString());
        XmlFormatter formatter = new XmlFormatter();
        FacadeLogin2 facadeLogin2 = new FacadeLogin2();
        String result = facadeLogin2.login2(url, version, user, password, language);
        xmlresponse = formatter.format(result);
        System.out.println(xmlresponse);

    }

    public String getXmlresponse() {
        return xmlresponse;
    }

    public void setXmlresponse(String xmlresponse) {
        this.xmlresponse = xmlresponse;
    }

    @Override
    public String toString() {
        return "LoginThread{" + "language=" + language + ", version=" + version + ", user=" + user + ", password=" + password + '}';
    }

}
