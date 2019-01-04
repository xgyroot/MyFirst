package com.example.miaoshademo.service.impl;

import com.example.miaoshademo.dao.PasswordMapper;
import com.example.miaoshademo.dao.UserMapper;
import com.example.miaoshademo.error.BusinessException;
import com.example.miaoshademo.error.EmBusinessError;
import com.example.miaoshademo.pojo.Password;
import com.example.miaoshademo.pojo.User;
import com.example.miaoshademo.service.UserService;
import com.example.miaoshademo.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (user == null){
            return null;
        }
        //通过用户id获取对应的用户加密密码信息
        Password password = passwordMapper.selectByUserId(user.getId());
        return convertFromPojo(user,password);
    }

    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if (StringUtils.isEmpty(userModel.getName())
            || userModel.getAge() == null
            || userModel.getGener() == null
            || StringUtils.isEmpty(userModel.getPhone())
        ){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        User user = convertUserFromModel(userModel);
        Password password = convertPasswordFromModel(userModel);
        //加入数据库
        userMapper.insertSelective(user);
        passwordMapper.insertSelective(password);
    }

    //实现model转变pojo方法
    private User convertUserFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userModel,user);
        return user;
    }

    private Password convertPasswordFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        Password password = new Password();
        password.setEncrptPassword(userModel.getEncrptPassword());
        password.setUserId(userModel.getId());
        return password;
    }

    //实现pojo转变model方法
    private UserModel convertFromPojo(User user,Password password){
        if (user == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user,userModel);
        if (password.getEncrptPassword() != null){
            userModel.setEncrptPassword(password.getEncrptPassword());
        }
        return userModel;
    }
}
