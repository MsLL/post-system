package com.upup.demo.postsystem.ng.answer.po;

import java.util.Date;
import java.io.Serializable;

/**
 * (Answer)实体类
 *
 * @author generate by easycode
 * @since 2021-03-17 23:31:49
 */
public class Answer implements Serializable {
    private static final long serialVersionUID = 910168898835404852L;
    
    private Integer id;

    private Integer userId;
    
    private Integer postId;
    
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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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