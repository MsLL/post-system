package com.upup.demo.postsystem.ng.comment.service.impl;

import com.upup.demo.postsystem.bss.user.model.UserModel;
import com.upup.demo.postsystem.bss.user.service.UserService;
import com.upup.demo.postsystem.ng.comment.model.CommentWrapper;
import com.upup.demo.postsystem.ng.comment.po.Comment;
import com.upup.demo.postsystem.ng.comment.dao.CommentDao;
import com.upup.demo.postsystem.ng.comment.model.CommentQueryParam;
import com.upup.demo.postsystem.ng.comment.service.CommentService;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Comment)表服务实现类
 *
 * @author generate by easycode
 * @since 2021-03-21 12:02:04
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserService userService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CommentWrapper findById(Integer id) {
        Comment comment = commentDao.findById(id);
        UserModel userModel = userService.findById(comment.getUserId()).get();
        CommentWrapper commentWrapper = mapComment(comment);
        commentWrapper.setUserName(userModel.getName());
        return commentWrapper;
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<CommentWrapper> list(CommentQueryParam queryParam) {
        List<CommentWrapper> commentWrappers = commentDao.list(queryParam)
            .stream()
            .map(comment -> mapComment(comment))
            .collect(Collectors.toList());
        attachUserName(commentWrappers);
        return commentWrappers;
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
        return comment;
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

    private CommentWrapper mapComment(Comment comment) {
        CommentWrapper commentWrapper = CommentWrapper.builder().build();
        BeanUtils.copyProperties(comment, commentWrapper);
        return commentWrapper;
    }

    private void attachUserName(List<CommentWrapper> commentWrappers) {
        int[] userIds = commentWrappers
            .stream()
            .mapToInt(CommentWrapper::getId)
            .toArray();
        Map<Integer, UserModel> userMap = userService.findByIds(userIds)
            .stream()
            .collect(Collectors.toMap(UserModel::getId, userModel -> userModel));
        commentWrappers.forEach(commentWrapper -> {
            UserModel userModel = userMap.get(commentWrapper.getUserId());
            if (userModel != null) {
                commentWrapper.setUserName(userModel.getName());
            }
        });
    }
}