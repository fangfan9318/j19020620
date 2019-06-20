package com.qf.j1902.mapper;

import com.qf.j1902.pojo.ZhongChou;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/1.
 */
public interface ZhongChouMapper {
    //将众筹信息添加到数据库
/*private int projectid;
    private String projectType;//项目分类
    private String projectTag;//标签
    private String projectName;//项目名称
    private String jianjie;//一句话简介
    private String money;//筹资金额
    private String days;//筹资天数
    private String titleImg;//项目头图
    private String xiangqingImg;//项目详情
    private String introduce;//自我介绍
    private String introduces;//详细自我介绍
    private String phonenumber;//联系电话
    private String telephone;//客服电话
    private String id;//用户的id
    private String statement;*/
    public boolean addZhongChou(@Param("projectType")String projectType,
                                @Param("projectTag")String projectTag,
                                @Param("projectName")String projectName,
                                @Param("jianjie")String jianjie,
                                @Param("money")String money,
                                @Param("days")String days,
                                @Param("titleImg")String titleImg,
                                @Param("xiangqingImg")String xiangqingImg,
                                @Param("introduce")String introduce,
                                @Param("introduces")String introduces,
                                @Param("phonenumber")String phonenumber,
                                @Param("telephone")String telephone,
                                @Param("id")int id,
                                @Param("statement")String statement,
                                @Param("starttime")String starttime,
                                @Param("endtime")String endtime);
    //根据用户id,项目statement查询众筹
    public ZhongChou getZhongChou(@Param("id") int id,
                                  @Param("statement") String statement);
    //根据用户id修改众筹
    public boolean updateZhongChou(@Param("id")int id,
                                    @Param("projectType")String projectType,
                                   @Param("projectTag")String projectTag,
                                   @Param("projectName")String projectName,
                                   @Param("jianjie")String jianjie,
                                   @Param("money")String money,
                                   @Param("days")String days,
                                   @Param("titleImg")String titleImg,
                                   @Param("xiangqingImg")String xiangqingImg,
                                   @Param("introduce")String introduce,
                                   @Param("introduces")String introduces,
                                   @Param("phonenumber")String phonenumber,
                                   @Param("telephone")String telephone,
                                   @Param("statement")String statement,
                                   @Param("starttime")String starttime,
                                   @Param("endtime")String endtime);
    //根据用户id修改--将易付宝账号及法人身份证添加到众筹中
    public boolean updatexx(@Param("id")int id,
                            @Param("statement")String statement,
                            @Param("yifubao")String yifubao,
                            @Param("farenidcard")String farenidcard);
    //根据状态查询众筹
    public ArrayList<ZhongChou> getZhongChouBySta(String statement);
    //根据项目id查询
    public ZhongChou getZhongChouById(int projectid);

}
