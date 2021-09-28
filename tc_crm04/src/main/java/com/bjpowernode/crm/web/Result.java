package com.bjpowernode.crm.web;

import java.util.HashMap;
import java.util.Map;

public interface Result {
    Map SUCCESS = new HashMap(){{
        put("success", true);
        put("msg", "操作成功！");
    }};
}
