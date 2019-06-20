package com.qf.j1902.mapper;

import com.qf.j1902.pojo.ProjectType;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/30.
 */
public interface ProjectMapper {
    //查询所有项目分类
    public ArrayList<ProjectType> findAll();
    //新增项目类型
    public boolean addProjectType(@Param("projectType") String projectType,
                                  @Param("jianjie") String jianjie);
    //根据id查询项目类型信息
    public ProjectType getProjectTypeById(int id);
    //根据id修改项目类型
    public boolean updateProjectType(@Param("id") int id,
                                     @Param("projectType") String projectType,
                                     @Param("jianjie") String jianjie);
    //根据id删除项目类型
    public boolean delProjectType(int id);
}

