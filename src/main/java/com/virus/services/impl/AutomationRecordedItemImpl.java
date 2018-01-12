/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.services.impl;

import com.virus.entity.AutomationRecordedItemEntity;
import com.virus.repository.AutomationRecordedItemRepository;
import com.virus.services.AutomationRecordedItemService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author krodriguez
 */
@Service
public class AutomationRecordedItemImpl implements AutomationRecordedItemService {

    @Autowired
    private AutomationRecordedItemRepository automationRecordedItemRepository;

    private static final Log LOG = LogFactory.getLog(AutomationRecordedItemImpl.class);

    @Override
    public boolean removeItem(String key) {
        boolean response = false;
        try {
            AutomationRecordedItemEntity automationRecordedItemEntity = automationRecordedItemRepository.findBykeyari(key);
            if (automationRecordedItemEntity != null) {
                try {
                    automationRecordedItemRepository.delete(automationRecordedItemEntity);
                    response = true;
                } catch (Exception e) {
                    LOG.info("AutomationRecordedItemImpl.removeContact -  Failed Truncate KEY : [{0}], Error : {2} " + e + " " + key);
                }
            }
        } catch (Exception error) {
            LOG.error("AutomationRecordedItemImpl.removeContact -  Failed Search This Key : [{0}], ERROR : {2}  " + error + " " + key);

        } finally {
            LOG.info("AutomationRecordedItemImpl.removeContact -  Finalize Transaction To Delete Key [{0}] " + key);
            return response;
        }

    }
}
