package com.longxingyu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longxingyu.pojo.User;
import com.longxingyu.service.UserService;
import com.longxingyu.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 龙星宇
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-07 20:01:47
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




