package com.qf.j1902.mapper;

import com.qf.j1902.pojo.TagContent;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/31.
 */
public interface TagContentMapper {
    //查询所有的标签内容
    public ArrayList<TagContent> findAllTagContent();
}
