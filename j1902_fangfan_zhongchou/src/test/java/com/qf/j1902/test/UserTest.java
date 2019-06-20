package com.qf.j1902.test;

import com.qf.j1902.pojo.ProjectType;
import com.qf.j1902.pojo.User;
import com.qf.j1902.service.ProjectService;
import com.qf.j1902.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/27.
 */
public class UserTest {
    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-service.xml","spring-mybatis.xml");
        UserService userService = ac.getBean(UserService.class);
        ArrayList<User> member = userService.findUserByJs("member");
        System.out.println(member);
        ArrayList<User> user = userService.findUserByJs("user");
        System.out.println(user);
    }
    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-service.xml","spring-mybatis.xml");
        UserService userService = ac.getBean(UserService.class);
        ArrayList<User> userByStatus = userService.getUserByStatus("0");
        System.out.println(userByStatus);
    }
    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-service.xml","spring-mybatis.xml");
        ProjectService projectService = ac.getBean(ProjectService.class);
        ArrayList<ProjectType> all = projectService.findAll();
        System.out.println(all);
    }
}
