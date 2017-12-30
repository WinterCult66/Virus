
package com.virus.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class Crypt {
    
    public static void main(String [] args){
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        System.out.println(pe.encode("1234"));
        
    }
    
}
