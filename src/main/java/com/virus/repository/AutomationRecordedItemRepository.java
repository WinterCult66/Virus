/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.repository;

import com.virus.entity.AutomationRecordedItemEntity;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author krodriguez
 */
@Repository
public interface AutomationRecordedItemRepository extends JpaRepository<AutomationRecordedItemEntity, Serializable> {

    AutomationRecordedItemEntity findBykeyari(String key);

}
