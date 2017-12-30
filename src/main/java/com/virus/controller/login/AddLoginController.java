/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.controller.login;

import com.virus.entity.Userr;
import com.virus.pojos.AddLoginPojo;
import com.virus.repository.UserRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Kevin
 */
public class AddLoginController {
    
    @Autowired
    UserRepository userRepository;
    
    @PostMapping("/addlogin")
        @ResponseBody
    public Map seleniumApiPaymentRequest(AddLoginPojo addLoginPojo) {
        System.out.println("pojo add : " + addLoginPojo.toString());
        
        Userr userr = new Userr("", "", true);
        
        userRepository.save(userr);
        return null;
    }
    
}
