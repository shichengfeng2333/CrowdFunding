package com.zhiyou100.controller;

import com.zhiyou100.entity.User;
import com.zhiyou100.resonse.ResponseCode;
import com.zhiyou100.service.UserService;
import com.zhiyou100.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName UserController
 * @Description TODO
 * @Auther shi
 * @Date 2018/9/13 20:42
 * @Version 1.0
 **/
@Controller
@RequestMapping("/login")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @RequestMapping("/login.do")
    public void findByUsernameAndPassword(String usName, String usPassword, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //校验
        if (StringUtils.isBlank(usName) || StringUtils.isBlank(usPassword)) {
            ResponseUtil.responseFailure(resp, "用户名或密码为空", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
            return;
        }
        //数据库查询
        try {
            User user = userService.findByNameAndPassword(usName, usPassword);
            if (user == null) {
                ResponseUtil.responseFailure(resp, "用户名或密码错误", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
                return;
            } else {
                req.getSession().setAttribute("user", user);
                ResponseUtil.responseSuccess(resp, "login success", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
