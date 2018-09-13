package com.zhiyou100.service;

import com.zhiyou100.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
/*junit4整合spring*/
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit4 spring的配置文件的地址
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Test
    public void findById() {
        User user = userService.findById(1);
        System.out.println(user);
    }
    @Test
    public void findByUsname() {
        User user = userService.findByNameAndPassword("zhangsan","123456");
        System.out.println(user);
    }
}