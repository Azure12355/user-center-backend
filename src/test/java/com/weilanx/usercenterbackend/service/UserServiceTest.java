package com.weilanx.usercenterbackend.service;

import com.weilanx.usercenterbackend.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @program: user-center-backend
 * @description: user测试
 * @author: LyttonYang
 * @create: 2024-12-14 15:05
 */

@SpringBootTest
public class UserServiceTest {
    
    @Resource
    private UserService userService;
    
    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("weilan");
        user.setAccount("123");
        user.setAvatar("https://www.weilanx.com/upload/favicon.png");
        user.setGender(0);
        user.setPassword("123");
        user.setPhone("123");
        user.setEmail("123");
        
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        //非空校验
        String account = "weilanx";
        String password = "";
        String checkPassword = "123456789";
        long result = userService.userRegister(account, password, checkPassword);
        Assertions.assertEquals(-1, result);
        
        //账户长度校验
        account = "lan";
        result = userService.userRegister(account, password, checkPassword);
        Assertions.assertEquals(-1, result);
        
        //密码长度校验
        account = "weilanx";
        password = "123";
        result = userService.userRegister(account, password, checkPassword);
        Assertions.assertEquals(-1, result);
        
        //特殊字符校验
        account = "wei lan ~";
        password = "123456789";
        result = userService.userRegister(account, password, checkPassword);
        Assertions.assertEquals(-1, result);
        
        //重复账户校验
        account = "weilan";
        password = "123456789";
        result = userService.userRegister(account, password, checkPassword);
        Assertions.assertEquals(-1, result);
        
        //成功注册
        account = "weilanx";
        password = "123456789";
        result = userService.userRegister(account, password, checkPassword);
        Assertions.assertTrue(result > 0);
    }
}
