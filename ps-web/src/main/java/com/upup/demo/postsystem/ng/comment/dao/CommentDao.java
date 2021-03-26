package com.upup.demo.postsystem.ng.comment.dao;

import com.upup.demo.postsystem.ng.comment.po.Comment;
import com.upup.demo.postsystem.ng.comment.model.CommentQueryParam;
import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author generate by easycode
 * @since 2021-03-21 12:02:02
 */
public interface CommentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment findById(Integer id);

    /**
     * 查询指定行数据
     * @return 对象列表
     */
    List<Comment> list(CommentQueryParam queryParam);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);
}