package com.qf.j1902.pojo;

/**
 * Created by Administrator on 2019/5/31.
 */
public class ZhongChou {
    private int projectid;
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
    private int id;//用户的id
    private String statement;//众筹状态,0--未提交(默认),1-提交成功,2-众筹中,3-申请驳回,4-失败,5-成功
    private String starttime;
    private String endtime;
    private String yifubao;//企业易付宝账号
    private String farenidcard;//企业法人身份证号码

    public ZhongChou() {
    }

    public ZhongChou(int projectid, String projectType, String projectTag, String projectName, String jianjie, String money, String days, String titleImg, String xiangqingImg, String introduce, String introduces, String phonenumber, String telephone, int id, String statement, String starttime, String endtime, String yifubao, String farenidcard) {
        this.projectid = projectid;
        this.projectType = projectType;
        this.projectTag = projectTag;
        this.projectName = projectName;
        this.jianjie = jianjie;
        this.money = money;
        this.days = days;
        this.titleImg = titleImg;
        this.xiangqingImg = xiangqingImg;
        this.introduce = introduce;
        this.introduces = introduces;
        this.phonenumber = phonenumber;
        this.telephone = telephone;
        this.id = id;
        this.statement = statement;
        this.starttime = starttime;
        this.endtime = endtime;
        this.yifubao = yifubao;
        this.farenidcard = farenidcard;
    }

    @Override
    public String toString() {
        return "ZhongChou{" +
                "projectid=" + projectid +
                ", projectType='" + projectType + '\'' +
                ", projectTag='" + projectTag + '\'' +
                ", projectName='" + projectName + '\'' +
                ", jianjie='" + jianjie + '\'' +
                ", money='" + money + '\'' +
                ", days='" + days + '\'' +
                ", titleImg='" + titleImg + '\'' +
                ", xiangqingImg='" + xiangqingImg + '\'' +
                ", introduce='" + introduce + '\'' +
                ", introduces='" + introduces + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", telephone='" + telephone + '\'' +
                ", id=" + id +
                ", statement='" + statement + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", yifubao='" + yifubao + '\'' +
                ", farenidcard='" + farenidcard + '\'' +
                '}';
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectTag() {
        return projectTag;
    }

    public void setProjectTag(String projectTag) {
        this.projectTag = projectTag;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getXiangqingImg() {
        return xiangqingImg;
    }

    public void setXiangqingImg(String xiangqingImg) {
        this.xiangqingImg = xiangqingImg;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIntroduces() {
        return introduces;
    }

    public void setIntroduces(String introduces) {
        this.introduces = introduces;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getYifubao() {
        return yifubao;
    }

    public void setYifubao(String yifubao) {
        this.yifubao = yifubao;
    }

    public String getFarenidcard() {
        return farenidcard;
    }

    public void setFarenidcard(String farenidcard) {
        this.farenidcard = farenidcard;
    }
}
