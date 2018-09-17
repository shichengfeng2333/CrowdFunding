package com.zhiyou100.controller;

import com.aliyuncs.exceptions.ClientException;
import com.zhiyou100.entity.User;
import com.zhiyou100.resonse.ResponseCode;
import com.zhiyou100.service.MailService;
import com.zhiyou100.service.SMSService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

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
    @Autowired
    SMSService smsService;
    @Autowired
    MailService mailService;

    @RequestMapping("/login.do")
    public void findByUsernameAndPassword(String usName, String usPassword, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //校验
        if (StringUtils.isBlank(usName) || StringUtils.isBlank(usPassword)) {
            ResponseUtil.responseFailure(resp, "用户名或密码为空", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
            return;
        }
        //数据库查询
        User user = userService.findByNameAndPassword(usName, usPassword);
        if (user == null) {
            ResponseUtil.responseFailure(resp, "用户名或密码错误", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
            return;
        } else {
            req.getSession().setAttribute("user", user);
            ResponseUtil.responseSuccess(resp, "login success", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
            return;
        }
    }

    @RequestMapping("/verifyCode.do")
    public void verifyCode(HttpServletRequest req, HttpServletResponse resp) throws ClientException, IOException {
        String cellphone = req.getParameter("cellphone");
        String usName = req.getParameter("usName");
        String password = req.getParameter("password");
        if (cellphone.contains("@")) {
            String usCode = userService.findByEmail(cellphone);
            if (usCode != null) {
                ResponseUtil.responseFailure(resp, "邮箱已注册", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
                return;
            }
            boolean doRegister = mailService.doRegister(usName, password, cellphone);
            if (doRegister) {
                ResponseUtil.responseSuccess(resp, "邮箱发送成功", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
            } else {
                ResponseUtil.responseFailure(resp, "邮箱未发送", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
            }
        } else {
            String code = smsService.verifyCode(cellphone, usName);
            //保存验证码
            req.getSession().setAttribute("code", code);
            //设置验证码时长
            long current = System.currentTimeMillis();
            long expireTime = current + 1000 * 60 * 5;
            req.getSession().setAttribute("codeExpireTime", expireTime);
            ResponseUtil.responseSuccess(resp, code, ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
        }
    }

    @RequestMapping("/register.do")
    public void insertUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String tel = req.getParameter("cellphone");
        String name = req.getParameter("usName");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        Long expireTime = (Long) session.getAttribute("codeExpireTime");
        String codeObj = (String) session.getAttribute("code");
        long current = System.currentTimeMillis();
        if (current > expireTime) {
            //过期
            ResponseUtil.responseFailure(resp, "验证码过期", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);

        } else if (codeObj.equals(code)) {
            User user = new User();
            user.setUsPhone(tel);
            user.setUsName(name);
            user.setUsPassword(password);
            user.setUsCreateTime(new Date());
            userService.insertSelective(user);
            ResponseUtil.responseSuccess(resp, "注册成功", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
        } else {
            ResponseUtil.responseFailure(resp, "验证码错误", ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);

        }
    }

    @RequestMapping("/mailVerify.do")
    public void mailVerify(String code, String email, HttpServletResponse resp) throws IOException {
        String usCode = userService.findByEmail(email);
        if (usCode != null) {
            //req.getRequestDispatcher("192.168.203.27:8080/mailFailure.html");
            resp.sendRedirect("http://192.168.203.27:8080/mailFailure.html");
        }
        if (code.equals(usCode)) {
            //req.getRequestDispatcher("192.168.203.27:8080/mail.html");
            resp.sendRedirect("http://192.168.203.27:8080/mailFailure.html");
        } else {
            //req.getRequestDispatcher("192.168.203.27:8080/mailFailure.html");
            resp.sendRedirect("http://192.168.203.27:8080/mailFailure.html");
        }
    }
}
