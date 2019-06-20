package com.qf.j1902.pojo;

/**
 * Created by Administrator on 2019/6/2.
 */
public class Repay {
    private int repayid;
    private String repaytype;
    private String jine;
    private String neirong;
    private String picture;
    private String repaycount;
    private String xiangou;
    private String yunfei;
    private String fapiao;
    private String repaytime;
    private int projectid;

    public Repay() {
    }

    public Repay(String repaytype, String jine, String neirong, String picture, String repaycount, String xiangou, String yunfei, String fapiao, String repaytime, int projectid) {
        this.repaytype = repaytype;
        this.jine = jine;
        this.neirong = neirong;
        this.picture = picture;
        this.repaycount = repaycount;
        this.xiangou = xiangou;
        this.yunfei = yunfei;
        this.fapiao = fapiao;
        this.repaytime = repaytime;
        this.projectid = projectid;
    }

    public Repay(int repayid, String repaytype, String jine, String neirong, String picture, String repaycount, String xiangou, String yunfei, String fapiao, String repaytime, int projectid) {
        this.repayid = repayid;
        this.repaytype = repaytype;
        this.jine = jine;
        this.neirong = neirong;
        this.picture = picture;
        this.repaycount = repaycount;
        this.xiangou = xiangou;
        this.yunfei = yunfei;
        this.fapiao = fapiao;
        this.repaytime = repaytime;
        this.projectid = projectid;
    }

    @Override
    public String toString() {
        return "Repay{" +
                "repayid=" + repayid +
                ", repaytype='" + repaytype + '\'' +
                ", jine='" + jine + '\'' +
                ", neirong='" + neirong + '\'' +
                ", picture='" + picture + '\'' +
                ", repaycount='" + repaycount + '\'' +
                ", xiangou='" + xiangou + '\'' +
                ", yunfei='" + yunfei + '\'' +
                ", fapiao='" + fapiao + '\'' +
                ", repaytime='" + repaytime + '\'' +
                ", projectid=" + projectid +
                '}';
    }

    public int getRepayid() {
        return repayid;
    }

    public void setRepayid(int repayid) {
        this.repayid = repayid;
    }

    public String getRepaytype() {
        return repaytype;
    }

    public void setRepaytype(String repaytype) {
        this.repaytype = repaytype;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRepaycount() {
        return repaycount;
    }

    public void setRepaycount(String repaycount) {
        this.repaycount = repaycount;
    }

    public String getXiangou() {
        return xiangou;
    }

    public void setXiangou(String xiangou) {
        this.xiangou = xiangou;
    }

    public String getYunfei() {
        return yunfei;
    }

    public void setYunfei(String yunfei) {
        this.yunfei = yunfei;
    }

    public String getFapiao() {
        return fapiao;
    }

    public void setFapiao(String fapiao) {
        this.fapiao = fapiao;
    }

    public String getRepaytime() {
        return repaytime;
    }

    public void setRepaytime(String repaytime) {
        this.repaytime = repaytime;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }
}
