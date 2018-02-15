/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.repository;

import com.virus.entity.ProductsSales2WSEntity;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author krodriguez
 */
public interface ProductsSales2WSRepository extends JpaRepository<ProductsSales2WSEntity, Serializable> {
    
}
