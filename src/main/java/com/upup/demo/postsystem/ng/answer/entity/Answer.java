package com.upup.demo.postsystem.ng.answer.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Answer)实体类
 *
 * @author generate by easycode
 * @since 2021-01-31 23:44:28
 */
public class Answer implements Serializable {
    private static final long serialVersionUID = 767275426812629533L;
    
    private Integer id;
    
    private Integer postId;
    
    private String content;
    
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

}