package com.zhiyou100.entity;

public class Questionall {
    private Integer questionid;

    private String questionname;

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public String getQuestionname() {
        return questionname;
    }

    public void setQuestionname(String questionname) {
        this.questionname = questionname == null ? null : questionname.trim();
    }
}