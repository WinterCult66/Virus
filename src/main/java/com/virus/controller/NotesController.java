package com.virus.controller;

//@author krodriguez
import com.virus.constant.ViewConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotesController {

    @GetMapping("/notes/shownotes")
    public String notesView() {
        return ViewConstant.NOTES;
    }

    @GetMapping("/notes/addnotes")
    public String addNotes() {
        return ViewConstant.NOTES_FORM;
    }

}
