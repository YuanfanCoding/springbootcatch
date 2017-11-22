package com.catchman.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String getVersion() {
        return "V1.0";
    }
}
