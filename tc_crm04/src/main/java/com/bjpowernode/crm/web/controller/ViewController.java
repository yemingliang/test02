package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ViewController {

    @Resource
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

    @GetMapping("/")
    public String root(@CookieValue(value = "FD4I93", required = false) String username,
                       @CookieValue(value = "LSI8JQ", required = false) String password) {
        if (StringUtils.isNoneBlank(username, password)) {
            String ip = request.getRemoteAddr();
            User user = userService.getUserForAutoLogin(username, password, ip);
            if (user != null) {
                // 判断是否登录的依据
                request.getSession().setAttribute("loginUser", user);
                return "redirect:/workbench/index.html";
            }
        }

        return "login";
    }

    // 为所有页面提供了访问支持！
    @GetMapping("/**/*.html") // 所有以.html结尾的路径
    public String workbenchMainIndex() {
        String uri = request.getRequestURI();
        System.out.println(uri);
        int dianIndex = uri.lastIndexOf(".");
        // viewName: "workbench/main/index"
        String viewName = uri.substring(1, dianIndex);
        return viewName;
    }

    /*@GetMapping("/workbench/index.html")
    public String workbenchIndex() {
        // "/workbench/index.html"
        String uri = request.getRequestURI();
        System.out.println(uri);
        int dianIndex = uri.lastIndexOf(".");
        // viewName: "workbench/index"
        String viewName = uri.substring(1, dianIndex);
        return viewName;
    }

    @GetMapping("/workbench/main/index.html")
    // "/workbench/main/index.html"
    public String workbenchMainIndex() {
        String uri = request.getRequestURI();
        System.out.println(uri);
        int dianIndex = uri.lastIndexOf(".");
        // viewName: "workbench/main/index"
        String viewName = uri.substring(1, dianIndex);
        return viewName;
    }*/
}
