package com.upup.demo.postsystem.ng.comment.service;

import com.upup.demo.postsystem.ng.comment.entity.Comment;
import com.upup.demo.postsystem.ng.comment.model.CommentQueryParam;
import java.util.List;

/**
 * (Comment)表服务接口
 *
 * @author generate by easycode
 * @since 2021-03-21 12:02:03
 */
public interface CommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment findById(Integer id);

    /**
     * 查询多条数据
     * @return 对象列表
     */
    List<Comment> list(CommentQueryParam queryParam);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
}