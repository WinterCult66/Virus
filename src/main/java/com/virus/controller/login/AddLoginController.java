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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Kevin
 */
@Controller
public class AddLoginController {
    
    @Autowired
    UserRepository userRepository;
    
    @PostMapping("/api/addlogin")
    @ResponseBody
    public Map seleniumApiPaymentRequest(AddLoginPojo addLoginPojo) {
        try {
            System.out.println("pojo add : " + addLoginPojo.toString());
            BCryptPasswordEncoder pe = new BCryptPasswordEncoder();            
            Userr userr = new Userr(addLoginPojo.getUsername(), pe.encode(addLoginPojo.getPassword()), true);            
            userRepository.save(userr);
            System.out.println("INSERT SUCCESS");
        } catch (Exception ex) {
            System.out.println("EX : " + ex);
        }
        return null;
    }
    
}
