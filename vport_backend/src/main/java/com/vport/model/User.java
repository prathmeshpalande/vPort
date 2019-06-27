package com.vport.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class User {

    private Credential credential;
    private String sessionKey;

    public User() {
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public User(Credential credential, String sessionKey) {
        this.credential = credential;
        this.sessionKey = sessionKey;
    }
}
