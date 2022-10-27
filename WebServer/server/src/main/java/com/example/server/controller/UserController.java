package com.example.server.controller;

import cn.hutool.core.util.StrUtil;
import com.example.server.common.Constants;
import com.example.server.common.Result;
import com.example.server.entity.User;
import com.example.server.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        if(userService.login(user)) {
            return Result.success("登录成功");
        }
        else return Result.error(Constants.CODE_400, "账户密码错误");
    }
}
