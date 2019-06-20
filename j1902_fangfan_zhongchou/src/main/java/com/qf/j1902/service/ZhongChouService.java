package com.qf.j1902.service;

import com.qf.j1902.pojo.Repay;
import com.qf.j1902.pojo.ZhongChou;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/1.
 */
public interface ZhongChouService {
    //将众筹信息添加到数据库
    public boolean addZhongChou(String projectType, String projectTag,
                                String projectName, String jianjie,
                                String money, String days,
                                String titleImg, String xiangqingImg,
                                String introduce, String introduces,
                                String phonenumber, String telephone,
                                int id, String statement,
                                String starttime, String endtime);
    //根据用户id,项目statement查询众筹
    public ZhongChou getZhongChou(int id, String statement);
    //根据用户id修改众筹
    public boolean updateZhongChou(int id, String projectType,
                                   String projectTag, String projectName,
                                   String jianjie, String money,
                                   String days, String titleImg,
                                   String xiangqingImg, String introduce,
                                   String introduces, String phonenumber,
                                   String telephone, String statement,
                                   String starttime, String endtime);
    //根据用户id修改--将易付宝账号及法人身份证添加到众筹中
    public boolean updatexx(int id,String statement, String yifubao, String farenidcard);
    //根据状态查询众筹
    public ArrayList<ZhongChou> getZhongChouBySta(String statement);
    //根据项目id查询
    public ZhongChou getZhongChouById(int projectid);



}
