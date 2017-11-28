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

    @RequestMapping(value = "/FBA", method = RequestMethod.GET)
    public String FBA(Model model) {
        return "FBA";
    }

    @RequestMapping(value = "/package", method = RequestMethod.GET)
    public String Package(Model model) {
        return "package";
    }

    @RequestMapping(value = "/software", method = RequestMethod.GET)
    public String software(Model model) {
        return "software";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(Model model) {
        return "about";
    }
}
