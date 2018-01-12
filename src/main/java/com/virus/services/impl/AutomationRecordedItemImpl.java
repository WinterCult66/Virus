/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.services.impl;

import com.virus.entity.AutomationRecordedItemEntity;
import com.virus.repository.AutomationRecordedItemRepository;
import com.virus.services.AutomationRecordedItemService;
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

    @Override
    public void removeContact(String key) {
        AutomationRecordedItemEntity automationRecordedItemEntity = automationRecordedItemRepository.findBykeyari(key);
        if (null != automationRecordedItemEntity) {
            automationRecordedItemRepository.delete(automationRecordedItemEntity);
        }

    }
}
