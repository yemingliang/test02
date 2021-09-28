package com.bjpowernode.crm.service;

import com.bjpowernode.crm.pojo.User;

public interface UserService {
    User getUser(String username, String password, String ip);

    User getUserForAutoLogin(String username, String password, String ip);

    void changePwd(String username, String password);
}
