/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.util;

import com.virus.constant.ViewConstant;
import com.virus.controller.ContactController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Kevin
 */
public class Util {

    private static final Log LOG = LogFactory.getLog(ContactController.class);

    public static String folderNumberAleatory() {
        Random generador = new Random();
        int d1 = generador.nextInt(100);
        int d2 = generador.nextInt(200);
        int d3 = generador.nextInt(300);
        int d4 = generador.nextInt(400);
        int foldernumber = d1 + d2 + d3 + d4;
        File dir = new File((ViewConstant.IMAGE_FOLDER + "\\" + foldernumber));
        String folderComplete = ViewConstant.IMAGE_FOLDER + "\\" + foldernumber;
        dir.mkdir();
        return folderComplete;
    }

    public static List<String> listFolder(String directory) {
        String sDirectorio = directory;
        List<String> imagesList = new ArrayList<String>();
        File f = new File(sDirectorio);
        if (f.exists()) {
            File[] ficheros = f.listFiles();
            for (int i = 0; i < ficheros.length; i++) {
                String[] parts = ficheros[i].getAbsolutePath().split("\\\\");
                int partsInt = parts.length;
                imagesList.add(ViewConstant.IMAGE_URL + parts[partsInt - 2] + "\\" + parts[partsInt - 1]);
                LOG.info("Send Paths Images: >>>>> " + ViewConstant.IMAGE_URL + parts[partsInt - 2] + "\\" + parts[partsInt - 1]);
            }
        } else {
            LOG.info(ViewConstant.ERROR_FOLDER);
        }
        return imagesList;
    }
}
