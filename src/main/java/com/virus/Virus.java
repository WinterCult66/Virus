package com.virus;

import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Virus {

    private static final Log LOG = LogFactory.getLog(Virus.class);

    @Value("${image.url}")
    private String imageURL;
    @Value("${image.folder}")
    private String folder;
    @Value("${selenium.chrome}")
    private String seleniumChrome;
    @Value("${selenium.firefox}")
    private String seleniumFirefox;
    @Value("${csv.folder}")
    private String csvFolder;
    @Value("${selenium.edge}")
    private String seleniumEdge;
    @Value("${scheduler}")
    private boolean scheduler;
    @Value("${fixed.rate}")
    private String fixedrate;
    @Value("${quantity.transactions}")
    private String quantityTransactions;
    
    
    
    

    public static void main(String[] args) {

        LOG.info("Start Main");
        SpringApplication.run(Virus.class, args);
        System.out.println();
    }

    @PostConstruct
    private void init() {
        LOG.info("# Images URL  : {}       " + imageURL);
        LOG.info("# ImageFolder   : {}       " + folder);
        LOG.info("# Selenium Folder chrome   : {}       " + seleniumChrome);
        LOG.info("# Selenium Folder Edge  : {}    " + seleniumEdge);
        LOG.info("# Selenium Folder Firefox  : {}    " + seleniumFirefox);
        LOG.info("# csvFolder   : {}       " + csvFolder);
        LOG.info("# scheduler   : {}       " + scheduler);
        LOG.info("# Fixedrate   : {}       " + fixedrate);
        LOG.info("# Quantity Transactions   : {}       " + quantityTransactions);

    }
}
