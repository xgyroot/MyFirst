package com.example.miaoshademo;

import com.example.miaoshademo.dao.UserMapper;
import com.example.miaoshademo.pojo.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.example.miaoshademo.dao")
@RestController
public class MiaoshademoApplication {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String Home(){
        User user = userMapper.selectByPrimaryKey(1);
        if (user==null){
            return "用户不存在";
        }else {
            return user.getName();
        }
    }

    @RequestMapping("/hello")
    public String Hello(){
        return "helloworld!";
    }

    public static void main(String[] args) {
        SpringApplication.run(MiaoshademoApplication.class, args);
    }
}
