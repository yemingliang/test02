package com.bjpowernode.crm.web.controller;

import com.bjpowernode.crm.pojo.Type;
import com.bjpowernode.crm.service.TypeService;
import com.bjpowernode.crm.web.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

//@ResponseBody // 类中所有的方法的返回值都会被转换为json并响应给客户端
//@Controller
@RestController // @Controller+@ResponseBody
@RequestMapping("type")
public class TypeController {

    @Resource
    private TypeService typeService;

    @GetMapping("types.json")
    public List types() {
        return typeService.getAll();
    }

    @GetMapping("type.json")
    // Controller层中的方法参数必须是具体的类型，不可以使用接口类型
    public Type type(String id) {
        return typeService.get(id);
    }

    @GetMapping("exists.json")
    public boolean exists(String id) {
        return typeService.getExists(id);
    }

    @PostMapping("save.do")
    public Map save(Type type) {
        typeService.save(type);
        return Result.SUCCESS;
    }

    @DeleteMapping("delete.do")
    public Map delete(String[] ids) {
        typeService.delete(ids);
        return Result.SUCCESS;
    }

    @PutMapping("update.do")
    public Map update(Type type) {
        typeService.update(type);
        return Result.SUCCESS;
    }


}
