/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.services.impl;

import com.virus.entity.AutomationRecordedDetailEntity;
import com.virus.repository.AutomationRecordedDetailRepository;
import com.virus.services.AutomationRecorderDetailService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kevin
 */
@Service
public class AutomationRecordedDetailImpl implements AutomationRecorderDetailService {
    
    private static final Log LOG = LogFactory.getLog(AutomationRecordedDetailImpl.class);
    
    @Autowired
    private AutomationRecordedDetailRepository automationRecordedDetailRepository;
    
    @Override
    public List<AutomationRecordedDetailEntity> listdeleteAlldetails(String key) {
        try {
            List<AutomationRecordedDetailEntity> details = automationRecordedDetailRepository.findBykeyitemari(key);
            if (details != null) {
                automationRecordedDetailRepository.delete(details);
            } else {
                LOG.info("Empty Results");
            }
            LOG.info("Delete Success {0} Details");
        } catch (Exception ex) {
            LOG.error("Error Delete AutomationRecordedDetailImpl.listdeleteAlldetails {0} " + ex);            
        }        
        return null;
    }
    
}
