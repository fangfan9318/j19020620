package com.qf.j1902.mapper;

import com.qf.j1902.pojo.User;
import org.apache.ibatis.annotations.Param;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/27.
 */
public interface UserMapper {
    //根据角色类型查询所有用户信息
    public ArrayList<User> findUserByJs(String jsType);
    //注册用户
    public boolean addUser(@Param("zhanghu")String zhanghu,
                           @Param("password")String password,
                           @Param("email")String email,
                           @Param("jsType")String jsType);
    //查询所有用户
    public ArrayList<User> findAll();
    //添加用户
    public boolean add(@Param("zhanghu")String zhanghu,
                       @Param("password")String password,
                       @Param("username")String username,
                       @Param("email")String email);
    //根据id获取用户信息
    public  User getUserById(int id);
    //根据id修改用户信息
    public boolean updateUser(@Param("id")int id,
                              @Param("zhanghu")String zhanghu,
                              @Param("username")String username,
                              @Param("email")String email);
    //根据id删除用户
    public boolean delUser(int id);
    //模糊查询
    public ArrayList<User> findUserMh(@Param("message")String message);
    //根据账户查询用户信息
    public User findUserByZh(String zhanghu);
    //将实名认证申请完成的用户信息录入数据库--根据账户修改
    public boolean updateByZh(@Param("zhanghu")String zhanghu,
                        @Param("email")String email,
                        @Param("username")String username,
                        @Param("idcard")String idcard,
                        @Param("phoneNumber")String phoneNumber,
                        @Param("userImg")String userImg,
                        @Param("zhType")String zhType,
                        @Param("status")String status
                        );
    //根据实名认证状态查询用户信息
    public ArrayList<User> getUserByStatus(String status);
    //根据账户修改审核状态及意见
    public boolean updateOpByZh(@Param("zhanghu")String zhanghu,
                              @Param("status")String status,
                              @Param("opinion")String opinion);

}
