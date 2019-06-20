package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.TagTypeMapper;
import com.qf.j1902.pojo.TagType;
import com.qf.j1902.service.TagTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/31.
 */
@Service
public class TagTypeServiceImpl implements TagTypeService {
    @Resource
    private TagTypeMapper tagTypeMapper;
    @Override
    public ArrayList<TagType> fingAllTagType() {
        return tagTypeMapper.fingAllTagType();
    }
}
