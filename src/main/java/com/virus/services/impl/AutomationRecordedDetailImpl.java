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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kevin
 */
@Service
public class AutomationRecordedDetailImpl implements AutomationRecorderDetailService {

    @Autowired
    private AutomationRecordedDetailRepository automationRecordedDetailRepository;



    @Override
    public List<AutomationRecordedDetailEntity> listAlldetails(String key) {
        List<AutomationRecordedDetailEntity> details  =  automationRecordedDetailRepository.findBykeyitemari(key);
        if(details != null){
        automationRecordedDetailRepository.delete(details);        
        }else{
            System.out.println("EMPTY RESULTS");
        }
            
        
        return null;
    }

}
