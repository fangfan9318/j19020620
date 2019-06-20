package com.qf.j1902.service;

import com.qf.j1902.pojo.TagContent;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/31.
 */
public interface TagContentService {
    //查询所有的标签内容
    public ArrayList<TagContent> findAllTagContent();
}
