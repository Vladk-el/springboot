package com.vladkel.springboot.app.controller;

import com.vladkel.springboot.beans.bean.Ping;
import com.vladkel.springboot.service.SBService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app")
public class AppController {

    private final SBService service;

    public AppController(SBService service) {
        this.service = service;
        System.out.println("init AppController with service " + service.sayHello());
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Ping ping() {
        return service.sayHelloToPing("/app");
    }

}
