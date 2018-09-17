package com.zhiyou100.service;

import com.zhiyou100.dao.UserMapper;
import com.zhiyou100.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Auther shi
 * @Date 2018/9/13 19:58
 * @Version 1.0
 **/
@Service
public class UserService {
    @Autowired
    UserMapper userdao;
    public User findByNameAndPassword(String usName,String usPassword){
        User user = userdao.selectByUsernameAndPassword(usName,usPassword);
        return  user;
    }
    public  User findById(int usId){
        User user = userdao.selectByPrimaryKey(usId);
        return user;
    }

    public int insertSelective(User user) {
        int row = userdao.insertSelective(user);
        return row;
    }

    public String findByEmail(String email) {
        User user = userdao.selectByEmail(email);
        if (user==null){
            return null;
        }
        String usCode = user.getUsCode();
        return usCode;
    }
}
