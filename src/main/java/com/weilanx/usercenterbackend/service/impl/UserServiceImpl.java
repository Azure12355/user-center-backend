package com.weilanx.usercenterbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilanx.usercenterbackend.model.User;
import com.weilanx.usercenterbackend.service.UserService;
import com.weilanx.usercenterbackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-12-14 14:56:43
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




