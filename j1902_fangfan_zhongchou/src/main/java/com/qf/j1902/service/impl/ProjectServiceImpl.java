package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.ProjectMapper;
import com.qf.j1902.pojo.ProjectType;
import com.qf.j1902.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/30.
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectMapper projectMapper;
    @Override
    public ArrayList<ProjectType> findAll() {
        return projectMapper.findAll();
    }

    @Override
    public boolean addProjectType(String projectType, String jianjie) {
        return projectMapper.addProjectType(projectType,jianjie);
    }

    @Override
    public ProjectType getProjectTypeById(int id) {
        return projectMapper.getProjectTypeById(id);
    }

    @Override
    public boolean updateProjectType(int id, String projectType, String jianjie) {
        return projectMapper.updateProjectType(id,projectType,jianjie);
    }

    @Override
    public boolean delProjectType(int id) {
        return projectMapper.delProjectType(id);
    }
}
