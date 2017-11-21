package com.catchman.controller;

import com.catchman.model.Userinfo;
import com.catchman.serviceImpl.IOhandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1")
public class userController {

    private Logger logger = LoggerFactory.getLogger(userController.class);

    /**
     * 测试hello
     * @return
     */
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("name","Dear");
        return new String (model.toString());
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Userinfo user(@RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "password", required = true) String password) {
        return IOhandler.getUserInfo(name,password);
    }

}
