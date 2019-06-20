package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.UserMapper;
import com.qf.j1902.pojo.User;
import com.qf.j1902.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/27.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public ArrayList<User> findUserByJs(String jsType) {
        return userMapper.findUserByJs(jsType);
    }

    @Override
    public boolean addUser(String zhanghu, String password,String email, String jsType) {
        return userMapper.addUser(zhanghu,password,email,jsType);
    }

    @Override
    public ArrayList<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public boolean add(String zhanghu,String password,String username, String email) {
        return userMapper.add(zhanghu,password,username,email);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean updateUser(int id,String zhanghu,String email,String username) {
        return userMapper.updateUser(id, zhanghu, email, username);
    }

    @Override
    public boolean delUser(int id) {
        return userMapper.delUser(id);
    }

    @Override
    public ArrayList<User> findUserMh(String message) {
        return userMapper.findUserMh(message);
    }

    @Override
    public User findUserByZh(String zhanghu) {
        return userMapper.findUserByZh(zhanghu);
    }

    @Override
    public boolean updateByZh(String zhanghu, String email, String username, String idcard, String phoneNumber, String userImg, String zhType, String status) {
        return userMapper.updateByZh(zhanghu,email,username,idcard,phoneNumber,userImg,zhType,status);
    }

    @Override
    public ArrayList<User> getUserByStatus(String status) {
        return userMapper.getUserByStatus(status);
    }

    @Override
    public boolean updateOpByZh(String zhanghu, String status, String opinion) {
        return userMapper.updateOpByZh(zhanghu,status,opinion);
    }

}
