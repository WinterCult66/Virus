/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.services.impl;

import com.virus.component.AutomationRecorderConverter;
import com.virus.entity.AutomationRecordedEntity;
import com.virus.model.AutomationRecorderModel;
import com.virus.repository.AutomationRecordedRepository;
import com.virus.services.AutomationRecorderService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author krodriguez
 */
@Service
public class AutomationRecorderImpl implements AutomationRecorderService {

    @Autowired
    private AutomationRecorderConverter automationRecorderConverter;

    @Autowired
    private AutomationRecordedRepository automationRecordedRepository;

    @Override
    public List<AutomationRecorderModel> listAllRecorders() {
        List<AutomationRecordedEntity> automationRecordedEntitys = (List<AutomationRecordedEntity>) automationRecordedRepository.findAll();
        List<AutomationRecorderModel> automationRecorderModel = new ArrayList<AutomationRecorderModel>();
        for (AutomationRecordedEntity automationRecordedEntity : automationRecordedEntitys) {
            automationRecorderModel.add(automationRecorderConverter.convertAutomationRecorderEntity2AutomationRecorderModel(automationRecordedEntity));
            
        }
        System.out.println("RETURN " + automationRecorderModel);
        return automationRecorderModel;
    }


}
