package com.zhiyou100.dao;

import com.zhiyou100.entity.Questionother;

public interface QuestionotherMapper {
    int deleteByPrimaryKey(Integer questionotherid);

    int insert(Questionother record);

    int insertSelective(Questionother record);

    Questionother selectByPrimaryKey(Integer questionotherid);

    int updateByPrimaryKeySelective(Questionother record);

    int updateByPrimaryKeyWithBLOBs(Questionother record);

    int updateByPrimaryKey(Questionother record);
}