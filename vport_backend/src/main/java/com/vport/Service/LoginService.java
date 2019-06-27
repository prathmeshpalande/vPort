package com.vport.Service;

import com.vport.model.Credential;
import com.vport.model.DummyUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private DummyUsers dummyUsers;

    public boolean processCredentials(Credential credential) {
        if(isExistUser(credential)) {
            return isValidUser(credential);
        }

        return false;
    }

    private boolean isValidUser(Credential credential) {
        return dummyUsers.getUsers().get(credential.getUserName()).getCredential()
                .getPassword().equals(credential.getPassword());
    }

    private boolean isExistUser(Credential credential) {
        return (dummyUsers.getUsers().get(credential.getUserName()) != null) ;

    }
}
