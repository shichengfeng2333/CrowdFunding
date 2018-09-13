package com.zhiyou100.dao;

import com.zhiyou100.entity.Questionall;

public interface QuestionallMapper {
    int deleteByPrimaryKey(Integer questionid);

    int insert(Questionall record);

    int insertSelective(Questionall record);

    Questionall selectByPrimaryKey(Integer questionid);

    int updateByPrimaryKeySelective(Questionall record);

    int updateByPrimaryKey(Questionall record);
}