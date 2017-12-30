package com.virus.controller;

// @author krodriguez
import com.virus.constant.ViewConstant;
import groovyjarjarasm.asm.tree.TryCatchBlockNode;
import javassist.bytecode.stackmap.BasicBlock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);

   

    @GetMapping("/login")
    public String showLoginForm(Model model,
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout) {
        LOG.info("METHOD:   SHOWLOGINFORM() -- PARMAS : " + " Error " + error + " Logout " + logout);
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return ViewConstant.LOGIN;
    }

    @GetMapping({"/loginsuccess", "/"})
    public String loginCheck() {
        try {
            LOG.info("Mhetod login Check");
            return "redirect:/home";
        } catch (Exception ex) {
            LOG.info("Mhetod login Check Error" + ex);
        }
        return "redirect:/contacts/showcontacts";
    }

}
