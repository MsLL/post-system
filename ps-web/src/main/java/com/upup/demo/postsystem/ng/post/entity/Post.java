package com.upup.demo.postsystem.ng.post.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Post)实体类
 *
 * @author generate by easycode
 * @since 2021-03-17 23:34:49
 */
public class Post implements Serializable {
    private static final long serialVersionUID = 967394308255397300L;
    
    private Integer id;

    private Integer userId;
    
    private String content;
    
    private String state;
    
    private Date createDatetime;
    
    private Date updateDatetime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}