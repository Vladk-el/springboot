package com.vladkel.springboot.app.controller;

import com.vladkel.springboot.beans.bean.Ping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app")
public class AppController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Ping ping() {
        System.out.println("/app");
        return new Ping();
    }

}
