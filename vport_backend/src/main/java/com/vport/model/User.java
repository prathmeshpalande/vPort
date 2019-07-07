package com.vport.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class User {

    private Credential credential;
    private String sessionKey;
    private List<Device> deviceList;

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

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public User(Credential credential, String sessionKey, List<Device> deviceList) {
        this.credential = credential;
        this.sessionKey = sessionKey;
        this.deviceList = deviceList;
    }
}
