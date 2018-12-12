package com.example.miaoshademo.service.impl;

import com.example.miaoshademo.dao.PasswordMapper;
import com.example.miaoshademo.dao.UserMapper;
import com.example.miaoshademo.pojo.Password;
import com.example.miaoshademo.pojo.User;
import com.example.miaoshademo.service.UserService;
import com.example.miaoshademo.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordMapper passwordMapper;

    @Override
    public UserModel getUserById(Integer id) {
        //调用userMapper获取到对应的用户
        User user = userMapper.selectByPrimaryKey(id);
        Password pw = passwordMapper.selectByUserId(id);
        if (user == null){
            return null;
        }
        if (pw == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user,userModel);
        userModel.setPassword(pw.getEncrptPassword());
        if (userModel == null){
            return null;
        }
        return userModel;
    }
}
