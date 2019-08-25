package com.atguigu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloControllerWithResponseBody {


    @RequestMapping("/hello2")
    public String hello(){
        return "Hello2 World2!";
    }

}
