package com.bjpowernode.crm.service.impl;

import com.bjpowernode.crm.mapper.ValueMapper;
import com.bjpowernode.crm.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ValueServiceImpl implements ValueService {

    @Autowired
    private ValueMapper valueMapper;

    public List getAll() {
        return valueMapper.getAll();
    }

    public Object get(Serializable id) {
        return valueMapper.get(id);
    }

    public int save(Object object) {
        return valueMapper.save(object);
    }

    public int delete(Serializable... id) {
        return valueMapper.delete(id);
    }

    public int update(Object object) {
        return valueMapper.update(object);
    }
}
