package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.TypeMapper;
import com.bjpowernode.crm.pojo.Type;
import com.bjpowernode.crm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    public List getAll() {
        return typeMapper.getAll();
    }

    public Type get(Serializable id) {
        return typeMapper.get(id);
    }

    public int save(Type type) {
        return typeMapper.save(type);
    }

    public int delete(Serializable... id) {

        // 选择：1,2,3
        // 使用中：1
        // 如何查出2、3

        // 删除2、3
        return typeMapper.delete(id);
    }

    public int update(Type type) {
        return typeMapper.update(type);
    }

    public boolean getExists(Serializable id) {
        return typeMapper.getExists(id);
    }
}
