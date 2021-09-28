package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.Value;
import com.bjpowernode.crm.service.ValueService;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.web.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("value")
public class ValueController {
    @Autowired
    private ValueService valueService;

    @GetMapping("values.json")
    public List values() {
        return valueService.getAll();
    }

    @GetMapping("value.json")
    public Object value(String id) {
        return valueService.get(id);
    }

    @PostMapping("save.do")
    public Map save(Value value) {
        // 通过程序生成随机id
        value.setId(UUIDUtil.getUUID());
        valueService.save(value);
        return Result.SUCCESS;
    }

    @DeleteMapping("delete.do")
    public Map delete(String[] ids) {
        valueService.delete(ids);
        return Result.SUCCESS;
    }

    @PutMapping("update.do")
    public Map update(Value value) {
        valueService.update(value);
        return Result.SUCCESS;
    }
}
