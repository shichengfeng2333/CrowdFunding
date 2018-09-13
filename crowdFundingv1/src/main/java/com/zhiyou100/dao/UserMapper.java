package com.zhiyou100.dao;

import com.zhiyou100.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer usId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer usId);


    User selectByUsernameAndPassword(@Param("usName") String usName , @Param("usPassword") String usPassword);


    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}