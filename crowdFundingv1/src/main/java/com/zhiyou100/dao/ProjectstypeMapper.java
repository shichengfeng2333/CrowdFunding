package com.zhiyou100.dao;

import com.zhiyou100.entity.Projectstype;

public interface ProjectstypeMapper {
    int deleteByPrimaryKey(Integer pstId);

    int insert(Projectstype record);

    int insertSelective(Projectstype record);

    Projectstype selectByPrimaryKey(Integer pstId);

    int updateByPrimaryKeySelective(Projectstype record);

    int updateByPrimaryKey(Projectstype record);
}