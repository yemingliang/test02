package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.mapper.UserMapper;
import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.UserService;
import com.bjpowernode.crm.utils.MD5Util;
import javafx.fxml.LoadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /*
        关于一些异常情况，如何反馈给客户端。
            1. 通过错误码，这些错误码是由服务方来制定的，因此用户看不懂这些错误码！---过时
            2. 直接告诉用户错误的原因，通过统一异常处理来完成，异常信息就是具体的提示消息
     */
    public User getUser(String username, String password, String ip) {

        // MD5加密
        password = MD5Util.getMD5(password);

        // 判断用户名和密码是否正确
        User user = userMapper.getUser(username, password);

        // 用户名或密码错误！
        if (user == null) {
            throw new LoginException("用户名或密码错误！");
        }

        // 是否过期
        String expireTime = user.getExpireTime();
        if (expireTime != null && !expireTime.equals("")) {
            // 获取当前距离1970年1月1号凌晨0点的毫秒值
            long now = System.currentTimeMillis();
            // expireTime: "2021-08-10 18:00:00"
            // now       : "2021-09-09 18:00:00"
            try {
                Date expireDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(expireTime);
                long expire = expireDate.getTime();// 获取日期对象距离1970年1月1号凌晨0点的毫秒值
                if (now > expire) {
                    throw new LoginException("账号已过期！");
                }
            } catch (ParseException e) {
                e.printStackTrace();
                throw new LoginException("账号已过期！请联系HR姐姐！");
            }
        }

        // 是否锁定
        if (!"1".equals(user.getLockStatus())) {
            throw new LoginException("账号已锁定！");
        }

        String allowIps = user.getAllowIps();
        // ip是否允许访问:为空时表示不限制IP，多个IP地址之间使用半角逗号隔开
        if (allowIps != null && !allowIps.equals("")) {
            // ip: 127.0.0.1
            // allowIps: 123.4.5.6,127.0.0.10
            String[] ips = allowIps.split(",");
            /*boolean allow = false;
            for (String allowIp : ips) {
                if (allowIp.equals(ip)) {
                    allow = true;
                    break;
                }
            }
            if (!allow) {
                throw new LoginException("您的IP不允许登录！");
            }*/

            List list = CollectionUtils.arrayToList(ips);
            if (!list.contains(ip)) {
                throw new LoginException("您的IP不允许登录！");
            }
        }

        return user;
    }

    public User getUserForAutoLogin(String username, String password, String ip) {
        // 判断用户名和密码是否正确
        User user = userMapper.getUser(username, password);

        // 用户名或密码错误！
        if (user == null) {
            return null;
        }

        // 是否过期
        String expireTime = user.getExpireTime();
        if (expireTime != null && !expireTime.equals("")) {
            // 获取当前距离1970年1月1号凌晨0点的毫秒值
            long now = System.currentTimeMillis();
            // expireTime: "2021-08-10 18:00:00"
            // now       : "2021-09-09 18:00:00"
            try {
                Date expireDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(expireTime);
                long expire = expireDate.getTime();// 获取日期对象距离1970年1月1号凌晨0点的毫秒值
                if (now > expire) {
                    return null;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

        // 是否锁定
        if (!"1".equals(user.getLockStatus())) {
            return null;
        }

        String allowIps = user.getAllowIps();
        // ip是否允许访问:为空时表示不限制IP，多个IP地址之间使用半角逗号隔开
        if (allowIps != null && !allowIps.equals("")) {
            // ip: 127.0.0.1
            // allowIps: 123.4.5.6,127.0.0.10
            String[] ips = allowIps.split(",");
            /*boolean allow = false;
            for (String allowIp : ips) {
                if (allowIp.equals(ip)) {
                    allow = true;
                    break;
                }
            }
            if (!allow) {
                throw new LoginException("您的IP不允许登录！");
            }*/

            List list = CollectionUtils.arrayToList(ips);
            if (!list.contains(ip)) {
                return null;
            }
        }

        return user;
    }

    public void changePwd(String username, String password) {
        password = MD5Util.getMD5(password);
        userMapper.changePwd(username, password);
    }
}
