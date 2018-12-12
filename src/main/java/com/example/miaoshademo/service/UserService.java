package com.example.miaoshademo.service;

import com.example.miaoshademo.service.model.UserModel;

public interface UserService {
    //根据id获取用户对象
    UserModel getUserById(Integer id);
}
