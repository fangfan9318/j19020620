package com.qf.j1902.service;

import com.qf.j1902.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/27.
 */
public interface UserService {
    //查询所有用户
    public ArrayList<User> findUserByJs(String jsType);
    //注册用户
    public boolean addUser(String zhanghu,String password,String email,String jsType);
    //查询所有用户
    public ArrayList<User> findAll();
    //添加用户
    public boolean add(String zhanghu,String password,String username,String email);
    //根据id获取用户信息
    public  User getUserById(int id);
    //根据id修改用户信息
    public boolean updateUser(int id,String zhanghu,String email,String username);
    //根据id删除用户
    public boolean delUser(int id);
    //模糊查询
    public ArrayList<User> findUserMh(String message);
    //根据账户查询用户信息
    public User findUserByZh(String zhanghu);
    //将实名认证申请完成的用户信息录入数据库--根据账户修改
    public boolean updateByZh(String zhanghu, String email,
                              String username, String idcard,
                              String phoneNumber, String userImg,
                              String zhType, String status);
    //根据实名认证状态查询用户信息
    public ArrayList<User> getUserByStatus(String status);
    //根据账户修改审核状态及意见
    public boolean updateOpByZh(String zhanghu, String status, String opinion);

}
