package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.UserService;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.web.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;


    @PostMapping("login.do")
    @ResponseBody
    public Map login(String username, String password, Boolean autoLogin) {
        String ip = request.getRemoteAddr();
        User user = userService.getUser(username, password, ip);

        request.getSession().setAttribute("loginUser", user);

        if (autoLogin) {
            int maxAge = 3600 * 24 * 10;
            // 将用户名和密码保存到cookie中
            Cookie cookie1 = new Cookie("FD4I93", username);
            cookie1.setMaxAge(maxAge);
            // 仅当访问路径是"/abc/*"时才会自动携带该cookie，其它路径是不携带的！
            // 如果不设置路径，则路径为当前资源所在的目录，“/user/”
            cookie1.setPath("/"); // 访问任何资源都会携带cookie
            password = MD5Util.getMD5(password);
            Cookie cookie2 = new Cookie("LSI8JQ", password);
            cookie2.setMaxAge(maxAge);
            cookie2.setPath("/");

            // 将cookie添加到响应对象中
            response.addCookie(cookie1);
            response.addCookie(cookie2);
        }

        return new HashMap(){{
            put("success", true);
        }};
    }

    @PostMapping("changePwd.do")
    @ResponseBody
    public Map changePwd(String username, String password) {
        userService.changePwd(username, password);
        return Result.SUCCESS;
    }

    @GetMapping("logout.do")
    public String logout() {
        // 将用户对象从session中移除
        request.getSession().removeAttribute("loginUser");
        // “破坏”免登陆功能，防止退不出！
        Cookie cookie = new Cookie("FD4I93", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }
}
