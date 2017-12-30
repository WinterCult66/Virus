/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.services;

import com.virus.model.AutomationRecorderModel;
import java.util.List;

/**
 *
 * @author krodriguez
 */
public interface AutomationRecorderService {
    
    public abstract List<AutomationRecorderModel> listAllRecorders();
}
