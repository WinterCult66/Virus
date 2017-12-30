package com.virus;

import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class Virus {

    private static final Log LOG = LogFactory.getLog(Virus.class);

    @Value("${image.url}")
    private String imageURL;
    @Value("${image.folder}")
    private String folder;
    @Value("${selenium.folder}")
    private String seleniumFolder;
    @Value("${csv.folder}")
    private String csvFolder;
//    @Value("${folder.img}")
//    private  String folderSystem;

    public static void main(String[] args) {

        LOG.info("Start Main");
        SpringApplication.run(Virus.class, args);
        System.out.println();
        //LOG.info(imageURL);

    }

    @PostConstruct
    private void init() {
        LOG.info("# Images URL  : {}       " + imageURL);
        LOG.info("# ImageFolder   : {}       " + folder);
        LOG.info("# seleniumFolder   : {}       " + seleniumFolder);
        LOG.info("# csvFolder   : {}       " + csvFolder);

    }
}
