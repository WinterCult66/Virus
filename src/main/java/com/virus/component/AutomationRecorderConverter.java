/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.component;

import com.virus.entity.AutomationRecordedEntity;
import com.virus.model.AutomationRecorderModel;
import org.springframework.stereotype.Component;

/**
 *
 * @author krodriguez
 */
@Component("automationRecorderConverter")
public class AutomationRecorderConverter {
    
    public AutomationRecordedEntity  convertAutomationRecorderModel2AutomationRecordedEntity(AutomationRecorderModel automationRecorderModel){
        
        AutomationRecordedEntity automationRecordedEntity = new AutomationRecordedEntity();
        automationRecordedEntity.setDivxpath(automationRecorderModel.getDivxpath());
        automationRecordedEntity.setOptionselect(automationRecorderModel.getOptionselect());
        automationRecordedEntity.setValuetosend(automationRecorderModel.getValuetosend());
        automationRecordedEntity.setCategoryidnew(automationRecorderModel.getCategoryidnew());
        
        return automationRecordedEntity;
         
    }
    
    
    
    

    public AutomationRecorderModel convertAutomationRecorderEntity2AutomationRecorderModel(AutomationRecordedEntity automationRecordedEntity) {
        AutomationRecorderModel automationRecorderModel = new AutomationRecorderModel();
        automationRecorderModel.setId(automationRecordedEntity.getId());
        automationRecorderModel.setDivxpath(automationRecordedEntity.getDivxpath());
        automationRecorderModel.setOptionselect(automationRecordedEntity.getOptionselect());
        automationRecorderModel.setValuetosend(automationRecordedEntity.getValuetosend());        
        return automationRecorderModel;
    }

}
