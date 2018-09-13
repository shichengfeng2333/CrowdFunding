package com.zhiyou100.dao;

import com.zhiyou100.entity.Projects;

public interface ProjectsMapper {
    int deleteByPrimaryKey(Integer psId);

    int insert(Projects record);

    int insertSelective(Projects record);

    Projects selectByPrimaryKey(Integer psId);

    int updateByPrimaryKeySelective(Projects record);

    int updateByPrimaryKey(Projects record);
}