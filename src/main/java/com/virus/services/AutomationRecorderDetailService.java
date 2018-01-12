/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.services;


import com.virus.entity.AutomationRecordedDetailEntity;
import java.util.List;

/**
 *
 * @author krodriguez
 */
public interface AutomationRecorderDetailService {
        
    public abstract List<AutomationRecordedDetailEntity> listdeleteAlldetails(String key);
}
