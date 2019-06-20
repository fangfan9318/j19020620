package com.qf.j1902.pojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/31.
 */
public class TagType {
    private int tagid;
    private String tagType;
    private ArrayList<TagContent> tagContents;

    public TagType() {
    }

    public TagType(int tagid, String tagType, ArrayList<TagContent> tagContents) {
        this.tagid = tagid;
        this.tagType = tagType;
        this.tagContents = tagContents;
    }

    @Override
    public String toString() {
        return "TagType{" +
                "tagid=" + tagid +
                ", tagType='" + tagType + '\'' +
                ", tagContents=" + tagContents +
                '}';
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public ArrayList<TagContent> getTagContents() {
        return tagContents;
    }

    public void setTagContents(ArrayList<TagContent> tagContents) {
        this.tagContents = tagContents;
    }
}
