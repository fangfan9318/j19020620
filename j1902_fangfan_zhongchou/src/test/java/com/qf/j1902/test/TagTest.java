package com.qf.j1902.test;

import com.qf.j1902.pojo.TagContent;
import com.qf.j1902.pojo.TagType;
import com.qf.j1902.service.TagContentService;
import com.qf.j1902.service.TagTypeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2019/5/31.
 */
public class TagTest {
    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mybatis.xml", "spring-service.xml");
        TagContentService tagContentService = ac.getBean(TagContentService.class);
        ArrayList<TagContent> allTagContent = tagContentService.findAllTagContent();
        System.out.println(allTagContent);
    }
    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mybatis.xml", "spring-service.xml");
        TagTypeService tagTypeService = ac.getBean(TagTypeService.class);
        ArrayList<TagType> tagTypes = tagTypeService.fingAllTagType();
        //System.out.println(tagTypes);
        for (TagType tagtype:tagTypes
             ) {
            System.out.println(tagtype);
        }
    }
    @Test
    public void test3(){
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currdate = format.format(d);
        System.out.println("现在的日期是：" + currdate);

        Calendar ca = Calendar.getInstance();
        //ca.setTime(currdate);
        ca.add(Calendar.DATE, 30);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d);
        System.out.println("增加天数以后的日期：" + enddate);

    }
}
