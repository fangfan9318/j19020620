package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.ZhongChouMapper;
import com.qf.j1902.pojo.ZhongChou;
import com.qf.j1902.service.ZhongChouService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/1.
 */
@Service
public class ZhongChouServiceImpl implements ZhongChouService {
    @Resource
    private ZhongChouMapper zhongChouMapper;
    //将众筹信息添加到数据库
    @Override
    public boolean addZhongChou(String projectType, String projectTag, String projectName, String jianjie, String money, String days, String titleImg, String xiangqingImg, String introduce, String introduces, String phonenumber, String telephone, int id, String statement,String starttime, String endtime) {
        return zhongChouMapper.addZhongChou(projectType,projectTag,projectName,jianjie,money,days,titleImg,xiangqingImg,introduce,introduces,phonenumber,telephone,id,statement,starttime,endtime);
    }

    @Override
    public ZhongChou getZhongChou(int id, String statement) {
        return zhongChouMapper.getZhongChou(id,statement);
    }

    @Override
    public boolean updateZhongChou(int id, String projectType, String projectTag, String projectName, String jianjie, String money, String days, String titleImg, String xiangqingImg, String introduce, String introduces, String phonenumber, String telephone, String statement, String starttime, String endtime) {
        return zhongChouMapper.updateZhongChou(id,projectType,projectTag,projectName,jianjie,money,days,titleImg,xiangqingImg,introduce,introduces,phonenumber,telephone,statement,starttime,endtime);
    }

    @Override
    public boolean updatexx(int id,String statement, String yifubao, String farenidcard) {
        return zhongChouMapper.updatexx(id,statement,yifubao,farenidcard);
    }

    @Override
    public ArrayList<ZhongChou> getZhongChouBySta(String statement) {
        return zhongChouMapper.getZhongChouBySta(statement);
    }

    @Override
    public ZhongChou getZhongChouById(int projectid) {
        return zhongChouMapper.getZhongChouById(projectid);
    }
}
