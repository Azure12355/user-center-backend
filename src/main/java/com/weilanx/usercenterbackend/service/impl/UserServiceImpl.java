package com.weilanx.usercenterbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilanx.usercenterbackend.model.User;
import com.weilanx.usercenterbackend.service.UserService;
import com.weilanx.usercenterbackend.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.util.regex.Pattern;

/**
 * 用户服务实现类
 *
 * @author lenovo
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-12-14 14:56:43
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    
    @Resource
    private UserMapper userMapper;
    
    @Override
    public long userRegister(String account, String password, String checkPassword) {
        //1.校验
        if (StringUtils.isAnyBlank(account, password, checkPassword)) {
            return -1;
        }
        if (account.length() < 6) {
            return -1;
        }
        if (password.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }   
        
        //校验账户不能包含特殊字符
        String validPattern = "^[a-zA-Z0-9]+$";
        boolean matches = Pattern.compile(validPattern).matcher(account).matches();
        if (!matches) {
            return -1;
        }
        
        //密码和校验密码相同
        if (!password.equals(checkPassword)) {
            return -1;
        }

        //账户不能重复
        Integer count = query().eq("account", account).count();
        if (count > 0) {
            return -1;
        }
        
        //2.加密
        final String SALT = "weilan";
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        
        //3.插入数据
        User user = new User();
        user.setAccount(account);
        user.setPassword(encryptedPassword);
        boolean saveResult = save(user);
        if (!saveResult) {
            return -1;
        }

        return user.getId();
    }
}




