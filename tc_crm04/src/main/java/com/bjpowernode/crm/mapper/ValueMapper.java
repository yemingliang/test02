package com.bjpowernode.crm.mapper;

import java.io.Serializable;
import java.util.List;

public interface ValueMapper {
    List getAll(); // 列表
    Object get(Serializable id); // 编辑时回显数据
    int save(Object object);
    int delete(Serializable... id);
    int update(Object object);
}
