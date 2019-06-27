package com.vport.controller;

import com.vport.Service.LoginService;
import com.vport.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String getCredentials(@RequestBody Credential credential) {

        return (loginService.processCredentials(credential)) ? "success" : "fail";
    }
}
