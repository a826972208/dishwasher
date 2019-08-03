package com.yc.intelligence.dishwasher.controller;

import com.yc.intelligence.dishwasher.common.Result;
import com.yc.intelligence.dishwasher.model.LoginVo;
import com.yc.intelligence.dishwasher.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public Result register(@RequestBody LoginVo loginVo){
        return accountService.register(loginVo);
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        return accountService.login(loginVo);
    }
}
