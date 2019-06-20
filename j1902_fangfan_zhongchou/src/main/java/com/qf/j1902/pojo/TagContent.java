package com.qf.j1902.pojo;

/**
 * Created by Administrator on 2019/5/31.
 */
public class TagContent {
    private int id;
    private String content;
    private int tagid;

    public TagContent() {
    }

    public TagContent(int id, String content, int tagid) {
        this.id = id;
        this.content = content;
        this.tagid = tagid;
    }

    @Override
    public String toString() {
        return "TagContent{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", tagid=" + tagid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }
}
