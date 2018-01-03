package com.catchman.controller;

import com.catchman.model.Userinfo;
import com.catchman.serviceImpl.IOhandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
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

    /**
     * 登录验证、获取用户信息接口
     *
     * @param name
     * @param password
     * @param mac
     * @return
     */
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Userinfo user(@RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "password", required = true) String password,
                         @RequestParam(value = "mac", required = true) String mac) {
        return IOhandler.getUserInfo(name, password, mac);
    }

    /**
     * 工具增加用户接口
     * @param ui
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public boolean user(@RequestBody Userinfo ui) {
        return IOhandler.addUserInfo(ui);
    }

    /**
     * 客户端退出时更新mac列表接口
     * @param ui
     * @return
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public boolean update(@RequestBody Userinfo ui) {
        return IOhandler.update(ui);
    }

}
