package com.zhiyou100.service;

import com.aliyuncs.exceptions.ClientException;
import com.zhiyou100.util.SMSUtil;
import org.springframework.stereotype.Service;

/**
 * @ClassName SMSService
 * @Description TODO
 * @Auther shi
 * @Date 2018/9/14 11:36
 * @Version 1.0
 **/
public class SMSService {
/*    public static void main(String[] args) throws ClientException {
        SMSService smsService = new SMSService();
        smsService.verifyCode("15036283367","shichengfeng");
    }*/
    public String verifyCode(String cellPhone,String usName) throws ClientException {
        String [] codes={"0","1","2","3","4","5","6","7","8","9"};
        //生成一个6位数的验证码
        String code="";
        for(int i=0;i<6;i++){
            int j=(int)(Math.random()*10);
            code+=codes[j];
        }
        SMSUtil.sendSms(cellPhone,usName,code);
        return code;
    }
}
