package com.virus.controller;

// @author Kevin
import com.virus.constant.ViewConstant;
import com.virus.model.ContactModel;
import com.virus.services.ContactService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    private static final Log LOG = LogFactory.getLog(ContactController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping("/cancel")
    public String cancel() {
        return "redirect:/contacts/showcontacts";
    }

    @GetMapping("/contactform")
    public String redirectContactForm(@RequestParam(name = "id", required = false) int id,
            Model model) {

        LOG.info("Contact-Form");
        try {
            ContactModel contactModel = new ContactModel();
            if (id != 0) {
                contactModel = contactService.findContactByIdModel(id);
            }

            model.addAttribute("contactModel", contactModel);
            return ViewConstant.CONTACT_FORM;
        } catch (Exception ex) {
            LOG.info("Contact-Form Error" + ex);
        }
        LOG.info("Redirect to  :/contacts/showcontacts");
        return "redirect:/contacts/showcontacts";
    }

    @PostMapping("/addcontact")
    public String addContact(@ModelAttribute(name = "contactModel") ContactModel contactModel,
            Model model) {
        LOG.info("CAMALEON" + contactModel.toString());
        LOG.info("AGREGAR USUARIO " + contactModel.getFirstName());

        if (contactService.addContact(contactModel) != null) {
            model.addAttribute("result", 1);
        } else {
            model.addAttribute("result", 0);
        }
        LOG.info("Redirect to :/contacts/showcontacts");
        return "redirect:/contacts/showcontacts";
    }

    @GetMapping("/showcontacts")
    public ModelAndView showContacts() {
        ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mav.addObject("username", user.getUsername());
        mav.addObject("contacts", contactService.listAllContacts());
       
        return mav;
    }

    @GetMapping("/removecontact")
    public ModelAndView removeContact(@RequestParam(name = "id", required = true) int id, Model model) {

        contactService.removeContact(id);
        model.addAttribute("result", 2);
        return showContacts();

    }    
    

}
