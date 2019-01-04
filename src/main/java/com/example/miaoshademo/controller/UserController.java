package com.example.miaoshademo.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.miaoshademo.controller.viewObject.UserVo;
import com.example.miaoshademo.error.BusinessException;
import com.example.miaoshademo.error.EmBusinessError;
import com.example.miaoshademo.response.CommonReturnType;
import com.example.miaoshademo.service.UserService;
import com.example.miaoshademo.service.model.UserModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //用户注册接口
    @RequestMapping("/register")
    @ResponseBody
    public CommonReturnType register(@RequestParam String telphone,@RequestParam String code,
                                     @RequestParam String name,@RequestParam Integer gender,
                                     @RequestParam Integer age,@RequestParam String password) throws BusinessException {
        //验证手机号和对应的code相符合
        String inSessionCode = (String)httpServletRequest.getSession().getAttribute(telphone);
        if (!StringUtils.equals(code,inSessionCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不符合");
        }
        //用户注册的流程
        UserModel userModel = new UserModel();
        userModel.setAge(age);
        userModel.setGener(gender);
        userModel.setName(name);
        userModel.setPhone(telphone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(MD5Encoder.encode(password.getBytes()));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    //用户获取otp短信接口
    @RequestMapping(value = "/getotp",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam String telphone){
        //需要按照一定的规则生成otp验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt+= 10000;
        String code = String.valueOf(randomInt);

        //将otp验证码同对应手机号关联,使用HttpSession的方式
        httpServletRequest.getSession().setAttribute(telphone,code);

        //将otp验证码通过短信通道发送给用户
        System.out.println("telphone = "+telphone+" & otpCode =" + code);

        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam Integer id) throws BusinessException{
        //调用service服务获取对应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);
        if (userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //将核心领域模型用户对象转化为可供UI使用的viewObject
        UserVo userVo = convertFromModel(userModel);
        return CommonReturnType.create(userVo);
    }

    private UserVo convertFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userModel,userVo);
        return userVo;
    }

}
