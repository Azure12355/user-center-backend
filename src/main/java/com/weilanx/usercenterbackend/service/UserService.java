package com.weilanx.usercenterbackend.service;

import com.weilanx.usercenterbackend.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lenovo
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-12-14 14:56:43
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * 
     * @param account 账户
     * @param password 密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String account, String password, String checkPassword);
}
