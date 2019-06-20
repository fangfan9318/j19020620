package com.qf.j1902.mapper;

import com.qf.j1902.pojo.Repay;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/2.
 */
public interface RepayMapper {
    //添加用户输入的回报到数据库
    /*private int repayid;
    private String repaytype;
    private String jine;
    private String neirong;
    private String picture;
    private String count;
    private String xiangou;
    private String yunfei;
    private String fapiao;
    private String repaytime;
    private int projectid;*/
    public boolean addRepay(@Param("repaytype")String repaytype,
                            @Param("jine")String jine,
                            @Param("neirong")String neirong,
                            @Param("picture")String picture,
                            @Param("repaycount")String repaycount,
                            @Param("xiangou")String xiangou,
                            @Param("yunfei")String yunfei,
                            @Param("fapiao")String fapiao,
                            @Param("repaytime")String repaytime,
                            @Param("projectid")int projectid);
    //根据projectid查询回报
    public ArrayList<Repay> getRepayById(int projectid);
}
