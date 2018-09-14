package com.zhiyou100.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration//表示测试的是Controller
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mvc.xml"})
public class UserControllerTest {
    @Autowired
    WebApplicationContext wac;
    MockMvc mockMvc;
    @Before//在所有测试执行之前，调用该方法
    public void setUp() throws Exception {
        mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void verifyCode() throws Exception {
        mockMvc.perform(post("/login/verifyCode.do")
                .param("cellphone","15036283367")
                .param("usName","石成峰"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void insertUser() {
    }
}