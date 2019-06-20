package com.qf.j1902.pojo;

/**
 * Created by Administrator on 2019/5/30.
 */
public class ProjectType {
    private int id;
    private String projectType;
    private String jianjie;

    public ProjectType() {
    }

    public ProjectType(int id, String projectType, String jianjie) {
        this.id = id;
        this.projectType = projectType;
        this.jianjie = jianjie;
    }

    @Override
    public String toString() {
        return "ProjectType{" +
                "id=" + id +
                ", projectType='" + projectType + '\'' +
                ", jianjie='" + jianjie + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }
}
