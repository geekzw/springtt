package com.gzw.service;

import com.alibaba.fastjson.JSON;
import com.gzw.enums.ResultCode;
import com.gzw.domain.ResultInfo;
import com.gzw.domain.User;
import com.gzw.mappers.UserMapper;
import com.gzw.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by gujian on 2017/6/23.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username){
        return userMapper.findByUsername(username);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    public ResultInfo loginIn(User user, HttpSession session){
        ResultInfo resultInfo;
        User user1 = userMapper.findByUsername(user.getUsername());
        if(user1 == null){
            resultInfo = ResultInfo.getErrorInfo(ResultCode.NO_USER);
        }else{
            if(user1.getPassword().equals(Md5Util.pwdDigest(user.getPassword()))){
                session.setAttribute("user",user);
                resultInfo = ResultInfo.getSuccessInfo(ResultCode.LOGIN_SUCCESS);
            }else{
                resultInfo = ResultInfo.getErrorInfo(ResultCode.ERROR_USERNAME_OR_PASSSWORD);
            }
        }
        return resultInfo;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    public ResultInfo register(User user){
        ResultInfo resultInfo;
        User user1 = userMapper.findByUsername(user.getUsername());
        if(user1!=null){
            resultInfo = ResultInfo.getErrorInfo(ResultCode.USER_EXIST);
        }else{
            user.setPassword(Md5Util.pwdDigest(user.getPassword()));
            if(userMapper.insert(user)) resultInfo = ResultInfo.getSuccessInfo(ResultCode.REGISTER_SUCCESS);
            resultInfo = ResultInfo.getErrorInfo(ResultCode.REGISTER_FAILE);
        }
        return resultInfo;
    }

    public ResultInfo<User> update(User user){
        ResultInfo<User> resultInfo;
        if(userMapper.update(user)){
            resultInfo = ResultInfo.getSuccessInfo(ResultCode.UPDATE_SUCCESS);
            resultInfo.setData(userMapper.findByUsername(user.getUsername()));
        }else{
            resultInfo = ResultInfo.getErrorInfo(ResultCode.UPDATE_ERROR);
        }
        return resultInfo;
    }
}
