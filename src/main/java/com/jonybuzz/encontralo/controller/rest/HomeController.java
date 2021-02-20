package com.jonybuzz.encontralo.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String paginaSolicitar() {
        return "index";
    }

}
