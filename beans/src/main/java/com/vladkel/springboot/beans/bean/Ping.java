package com.vladkel.springboot.beans.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Ping {

    @XmlElement
    public Date ping;

    @XmlElement
    public String path;

    public Ping() {
        ping = new Date();
    }

    public Ping(String path) {
        ping = new Date();
        this.path = path;
    }

}
