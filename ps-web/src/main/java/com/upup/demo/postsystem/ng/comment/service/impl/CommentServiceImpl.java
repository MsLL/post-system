package com.upup.demo.postsystem.ng.comment.service.impl;

import com.upup.demo.postsystem.ng.comment.entity.Comment;
import com.upup.demo.postsystem.ng.comment.dao.CommentDao;
import com.upup.demo.postsystem.ng.comment.model.CommentQueryParam;
import com.upup.demo.postsystem.ng.comment.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Comment)表服务实现类
 *
 * @author generate by easycode
 * @since 2021-03-21 12:02:04
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Comment findById(Integer id) {
        return this.commentDao.findById(id);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<Comment> list(CommentQueryParam queryParam) {
        return commentDao.list(queryParam);
    }

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment insert(Comment comment) {
        this.commentDao.insert(comment);
        return comment;
    }

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment update(Comment comment) {
        this.commentDao.update(comment);
        return this.findById(comment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.commentDao.deleteById(id) > 0;
    }
}