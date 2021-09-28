package com.bjpowernode.crm.mapper;

import com.bjpowernode.crm.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User getUser(@Param("username") String username,
                 @Param("password") String password);

    void changePwd(@Param("username") String username,
                   @Param("password") String password);
}
