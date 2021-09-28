package com.bjpowernode.crm.mapper;

import com.bjpowernode.crm.pojo.Type;

import java.io.Serializable;
import java.util.List;

public interface TypeMapper {
    List getAll(); // 列表
    Type get(Serializable id); // 编辑时回显数据
    int save(Type type);
    int delete(Serializable... id);
    int update(Type type);
    boolean getExists(Serializable id); // 主键的唯一性验证
}
