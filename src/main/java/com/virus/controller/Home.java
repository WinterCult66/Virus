package com.virus.controller;

import com.virus.constant.ViewConstant;
import com.virus.services.ContactService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

// @author krodriguez
@Controller

public class Home {

    @Autowired
    private ContactService contactService;

    private static final Log LOG = LogFactory.getLog(LoginController.class);

    @GetMapping("/home")
    public ModelAndView home() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView mav = new ModelAndView(ViewConstant.HOME);
        mav.addObject("username", user.getUsername());
        mav.addObject("countuser", contactService.listAllContacts().size());
        return mav;
    }

    @GetMapping("/home/methods")
    public String methods() {
        return "methodsemida";
    }

    @GetMapping("/home/dom")
    public String dom() {
        return "dom";
    }

    @GetMapping("/home/mex")
    public String mex() {
        return "mex";
    }

    @GetMapping("/home/doit")
    public String doit() {
        return "doit";
    }

    @GetMapping("/records/addrecords")
    public String addrecords() {
        return ViewConstant.ADDRECORDS;
    }

    

}
