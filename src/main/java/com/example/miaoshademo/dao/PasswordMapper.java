package com.example.miaoshademo.dao;

import com.example.miaoshademo.pojo.Password;

public interface PasswordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Password record);

    int insertSelective(Password record);

    Password selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Password record);

    int updateByPrimaryKey(Password record);

    Password selectByUserId(Integer userId);
}