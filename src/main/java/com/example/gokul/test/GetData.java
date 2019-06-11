package com.example.gokul.test;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.processing.Processor;

@Service
class GetData {

    @RequestMapping("/")
    @ResponseBody
    public String hello(){
       return "hi";
    }
}
