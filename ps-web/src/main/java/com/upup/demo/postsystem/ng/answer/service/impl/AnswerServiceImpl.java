package com.upup.demo.postsystem.ng.answer.service.impl;

import com.upup.demo.postsystem.bss.user.model.UserModel;
import com.upup.demo.postsystem.bss.user.service.UserService;
import com.upup.demo.postsystem.ng.answer.dao.AnswerDao;
import com.upup.demo.postsystem.ng.answer.model.AnswerQueryParam;
import com.upup.demo.postsystem.ng.answer.model.AnswerWrapper;
import com.upup.demo.postsystem.ng.answer.po.Answer;
import com.upup.demo.postsystem.ng.answer.service.AnswerService;
import com.upup.demo.postsystem.ng.comment.model.CommentQueryParam;
import com.upup.demo.postsystem.ng.comment.model.CommentWrapper;
import com.upup.demo.postsystem.ng.comment.service.CommentService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Answer)表服务实现类
 *
 * @author generate by easycode
 * @since 2021-01-31 23:44:32
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AnswerWrapper findById(Integer id) {
        Answer answer = answerDao.findById(id);
        UserModel userModel = userService.findById(answer.getUserId()).get();
        List<CommentWrapper> comments = commentService.list(CommentQueryParam.builder().answerIds(new int[] {answer.getId()}).build());
        AnswerWrapper answerWrapper = mapAnswer(answer);
        answerWrapper.setUserName(userModel.getName());
        answerWrapper.setComments(comments);
        return answerWrapper;
    }

    /**
     * 查询多条数据
     *
     * @param queryParam@return 对象列表
     */
    @Override
    public List<AnswerWrapper> list(AnswerQueryParam queryParam) {
        List<AnswerWrapper> answers = answerDao.list(queryParam)
            .stream()
            .map(answer -> mapAnswer(answer))
            .collect(Collectors.toList());
        attachUserName(answers);
        attachComments(answers);
        return answers;
    }

    /**
     * 新增数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    @Override
    public Answer insert(Answer answer) {
        this.answerDao.insert(answer);
        return answer;
    }

    /**
     * 修改数据
     *
     * @param answer 实例对象
     * @return 实例对象
     */
    @Override
    public Answer update(Answer answer) {
        this.answerDao.update(answer);
        return answer;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.answerDao.deleteById(id) > 0;
    }

    private AnswerWrapper mapAnswer(Answer answer) {
        AnswerWrapper answerWrapper = AnswerWrapper.builder().build();
        BeanUtils.copyProperties(answer, answerWrapper);
        return answerWrapper;
    }

    private void attachUserName(List<AnswerWrapper> answerWrappers) {
        int[] userIds = answerWrappers
            .stream()
            .mapToInt(AnswerWrapper::getUserId)
            .toArray();
        Map<Integer, UserModel> userMap = userService.findByIds(userIds)
            .stream()
            .collect(Collectors.toMap(UserModel::getId, userModel -> userModel));
        answerWrappers.forEach(answerWrapper -> {
            UserModel userModel = userMap.get(answerWrapper.getUserId());
            if (userModel != null) {
                answerWrapper.setUserName(userModel.getName());
            }
        });
    }

    private void attachComments(List<AnswerWrapper> answerWrappers) {
        int[] answerIds = answerWrappers
            .stream()
            .mapToInt(answerWrapper -> answerWrapper.getId())
            .toArray();
        Map<Integer, List<CommentWrapper>> comments = commentService
            .list(CommentQueryParam.builder().answerIds(answerIds).build())
            .stream()
            .collect(Collectors.groupingBy(CommentWrapper::getAnswerId));
        answerWrappers.forEach(answerWrapper -> {
            if(comments.get(answerWrapper.getId())!=null) {
                answerWrapper.setComments(comments.get(answerWrapper.getId()));
            }else {
                answerWrapper.setComments(new ArrayList<>());
            }
        });
    }
}