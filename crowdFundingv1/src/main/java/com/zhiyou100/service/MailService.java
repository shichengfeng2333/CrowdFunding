package com.zhiyou100.service;

import com.zhiyou100.dao.UserMapper;
import com.zhiyou100.entity.User;
import com.zhiyou100.util.CodeUtil;
import com.zhiyou100.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MailService {
	@Autowired
	UserMapper userDao;
	
	public boolean doRegister(String userName, String password, String email) {
		// 这里可以验证各字段是否为空
		//利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
		if(!email.matches("^\\w+@(\\w+\\.)+\\w+$")){
			return false;
		}
		//生成激活码
		String code=CodeUtil.generateUniqueCode();
		User user=new User();
		user.setUsCode(code);
		user.setUsEmail(email);
		user.setUsName(userName);
		user.setUsPassword(password);
		user.setUsCreateTime(new Date());
		user.setUsRole(0);
		//将用户保存到数据库
		int rows = userDao.insert(user);
		//保存成功则通过线程的方式给用户发送一封邮件
		if(rows>0){
			new Thread(new MailUtil(email, code)).start();
		return true;
		}else {
			return false;
		}
	}
/*	public boolean activeUser(String code) {
		UserDao userDao=new UserDaoImpl();
		if(userDao.activeUser(code)>0){
			return true;
		}else{
			return false;
		}
	}*/
}
