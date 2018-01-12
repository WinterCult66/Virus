/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.repository;

import com.virus.entity.AutomationRecordedDetailEntity;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author krodriguez
 */
@Repository
public interface AutomationRecordedDetailRepository extends JpaRepository<AutomationRecordedDetailEntity, Serializable> {

    List<AutomationRecordedDetailEntity> findBykeyitemari(String key);
}
