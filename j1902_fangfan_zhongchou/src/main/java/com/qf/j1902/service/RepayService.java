package com.qf.j1902.service;

import com.qf.j1902.pojo.Repay;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/2.
 */
public interface RepayService {
    //添加用户输入的回报到数据库
    public boolean addRepay(String repaytype,
                            String jine, String neirong,
                            String picture, String repaycount,
                            String xiangou, String yunfei,
                            String fapiao, String repaytime,
                           int projectid);
    //根据projectid查询回报
    public ArrayList<Repay> getRepayById(int projectid);
}
