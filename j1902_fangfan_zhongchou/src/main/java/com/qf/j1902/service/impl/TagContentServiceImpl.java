package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.TagContentMapper;
import com.qf.j1902.pojo.TagContent;
import com.qf.j1902.service.TagContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/31.
 */
@Service
public class TagContentServiceImpl implements TagContentService {
    @Resource
    private TagContentMapper tagContentMapper;
    @Override
    public ArrayList<TagContent> findAllTagContent() {
        return tagContentMapper.findAllTagContent();
    }
}
