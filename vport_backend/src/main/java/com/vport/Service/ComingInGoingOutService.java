package com.vport.Service;

import com.vport.controller.DeviceManagementController;
import com.vport.model.Credential;
import com.vport.model.DummyUsers;
import com.vport.model.GeneralResponseObject;
import com.vport.model.LogoutRequest;
import com.vport.util.HashGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ComingInGoingOutService {

    Logger logger = LoggerFactory.getLogger(ComingInGoingOutService.class);

    @Autowired
    private DummyUsers dummyUsers;

    public GeneralResponseObject processCredentials(Credential credential) {

        GeneralResponseObject response = new GeneralResponseObject();
        if(isExistUser(credential) && isValidUser(credential)) {
            String sessionKey = getSessionKey(credential);
            response = GeneralResponseObject.getDefaultObject(1);
            Map<String, String> responseData = new HashMap<>();
            responseData.put("sessionKey", sessionKey);
            response.setResponseData(responseData);
        }
        else {
            response = GeneralResponseObject.getDefaultObject(0);
            response.setResponseMessage("Username/Password Invalid!");
        }

        return response;
    }
    private String getSessionKey(Credential credential) {

        String sessionKey = HashGenerator.getMd5(credential.getUserName() + System.currentTimeMillis());
        dummyUsers.getUsers().get(credential.getUserName()).setSessionKey(sessionKey);

        return sessionKey;
    }

    private boolean isValidUser(Credential credential) {
        return dummyUsers.getUsers().get(credential.getUserName()).getCredential()
                .getPassword().equals(credential.getPassword());
    }

    private boolean isExistUser(Credential credential) {
        return (dummyUsers.getUsers().get(credential.getUserName()) != null) ;

    }

    public GeneralResponseObject logout(LogoutRequest logoutRequest) {
        dummyUsers.getUsers().get(logoutRequest.getUserName()).setSessionKey(null);
        GeneralResponseObject responseObject = GeneralResponseObject.getDefaultObject(1);
        return responseObject;
    }
}
