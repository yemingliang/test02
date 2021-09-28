package com.bjpowernode.crm.web.advice;

import com.bjpowernode.crm.exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // 对Controller的增强
public class MyExceptionHandler {
    @ExceptionHandler({LoginException.class})
    // e是捕获到的异常对象
    @ResponseBody
    public Map loginException(Exception e) {
        return new HashMap(){{
            put("success", false);
            put("msg", e.getMessage());
        }};
    }
}
