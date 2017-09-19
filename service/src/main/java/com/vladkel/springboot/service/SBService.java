package com.vladkel.springboot.service;

import com.vladkel.springboot.beans.bean.Ping;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SBService {

    private Date date;

    public SBService() {
        date = new Date();
        System.out.println("SBService created at " + sayHello());
    }

    public long sayHello() {
        return date.getTime();
    }

    public Ping sayHelloToPing(String path) {
        System.out.println("New ping from " + path);
        return new Ping(path);
    }

}
