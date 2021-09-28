package com.bjpowernode.crm.service;

import java.io.Serializable;
import java.util.List;

public interface ValueService {
    List getAll(); // 列表
    Object get(Serializable id); // 编辑时回显数据
    int save(Object object);
    int delete(Serializable... id);
    int update(Object object);
}
