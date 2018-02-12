/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.util;

import com.virus.entity.HistoryItemDetailEntity;
import com.virus.repository.HistoryItemDetailRepository;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author krodriguez
 */
public class SaveDetailMultiBrowser {

    private static final Logger LOG = Logger.getLogger(SaveDetailMultiBrowser.class.getName());

    HistoryItemDetailEntity historyItemDetailEntity;

    public void SaveDetailBrowser(String event, int status, String UUID, HistoryItemDetailRepository historyItemDetailRepository) {

        try {
            historyItemDetailEntity = new HistoryItemDetailEntity(event, status, UUID);
            LOG.info("Insert in to " + historyItemDetailEntity.toString());
            historyItemDetailRepository.save(historyItemDetailEntity);
        } catch (Exception ex) {
            LOG.info("Error Save Detail Multi Browser : " + ex);
        }
    }

}
