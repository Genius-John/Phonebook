package ru.geniusjohn.phonebook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {


    @GetMapping ("/")
    public String phoneBook() {
        return "phonebook";
    }

}
