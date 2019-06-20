package com.qf.j1902.service;

import com.qf.j1902.pojo.ProjectType;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/30.
 */
public interface ProjectService {
    //查询所有项目分类
    public ArrayList<ProjectType> findAll();
    //新增项目类型
    public boolean addProjectType(String projectType, String jianjie);
    //根据id查询项目类型信息
    public ProjectType getProjectTypeById(int id);
    //根据id修改项目类型
    public boolean updateProjectType(int id, String projectType, String jianjie);
    //根据id删除项目类型
    public boolean delProjectType(int id);
}
