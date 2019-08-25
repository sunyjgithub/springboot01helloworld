package com.atguigu.controller;


import com.atguigu.entity.Person;
import com.atguigu.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){

        return "Hello World!";
    }


    @ResponseBody
    @RequestMapping("/persons")
    public Person persons(String name){

        Person p=new Person();
        p.setAge("121");
        p.setName("sunyj");

        return  p;
    }

    @ResponseBody
    @RequestMapping("/exceptions")
    public Person exceptions(){

        Person p=new Person();
        p.setAge("121");
        p.setName("sunyj");
        if(p!=null) throw new RuntimeException("抛出一个异常");
        return  p;
    }

    @ResponseBody
    @RequestMapping("/getPersons")
    public ResponseEntity getPerson(){
        Person p=new Person();
        p.setAge("121");
        p.setName("sunyj");

        ResponseEntity responseEntity=new ResponseEntity(p, HttpStatus.OK);
        return responseEntity;
    }

    //controller中抛出异常进行测试。
    @RequestMapping("/home")
    public String home() throws Exception {
      // throw new Exception("Sam 错误");
        throw new MyException("101", "Sam 错误");

    }
}
