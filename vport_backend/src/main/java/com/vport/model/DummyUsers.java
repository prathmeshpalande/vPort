package com.vport.model;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DummyUsers implements InitializingBean {

    private HashMap<String, User> users;

    public DummyUsers() {
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        users = new HashMap<>();
        User user1 = new User(new Credential("user1@gmail.com", "abc123"), null);
        User user2 = new User(new Credential("user2@gmail.com", "abc456"), null);

        users.put("user1@gmail.com", user1);
        users.put("user2@gmail.com", user2);
    }
}

