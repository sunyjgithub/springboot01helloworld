package com.atguigu.controller;

import com.atguigu.entity.UserVo;
import com.atguigu.exception.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/{id}")
    public UserVo getUserById(@PathVariable Long id) {
        throw new BusinessException("1001", "根据ID查询用户异常");
    }
}