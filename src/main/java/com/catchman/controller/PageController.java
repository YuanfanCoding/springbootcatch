package com.catchman.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    private Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {
        return "index";
    }
}
