package com.vport.model;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import se.vidstige.jadb.JadbConnection;

@Component
public class JadbConnectionUniversal implements InitializingBean {

    private JadbConnection jadbConnection;

    public JadbConnectionUniversal() {

    }

    public JadbConnection getJadbConnection() {
        return jadbConnection;
    }

    public void setJadbConnection(JadbConnection jadbConnection) {
        this.jadbConnection = jadbConnection;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        jadbConnection = new JadbConnection();
    }
}
