package com.vport.controller;

import com.vport.Service.ComingInGoingOutService;
import com.vport.model.Credential;
import com.vport.model.GeneralResponseObject;
import com.vport.model.LogoutRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComingInGoingOutController {

    Logger logger = LoggerFactory.getLogger(ComingInGoingOutController.class);

    @Autowired
    private ComingInGoingOutService comingInGoingOutService;

    @PostMapping("/login")
    public GeneralResponseObject getCredentials(@RequestBody Credential credential) {
        logger.debug("Login Request Received");

        GeneralResponseObject responseObject = comingInGoingOutService.processCredentials(credential);

        logger.debug("Login Request Finished");
        return  responseObject;
    }
    @PostMapping("/logout")
    public GeneralResponseObject logout(@RequestBody LogoutRequest logoutRequest) {

        GeneralResponseObject responseObject = comingInGoingOutService.logout(logoutRequest);

        return responseObject;
    }
}
