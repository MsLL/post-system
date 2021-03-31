package com.upup.demo.postsystem.ng.post.service.impl;

import com.upup.demo.postsystem.bss.user.model.UserModel;
import com.upup.demo.postsystem.bss.user.service.UserService;
import com.upup.demo.postsystem.ng.answer.model.AnswerQueryParam;
import com.upup.demo.postsystem.ng.answer.model.AnswerWrapper;
import com.upup.demo.postsystem.ng.answer.service.AnswerService;
import com.upup.demo.postsystem.ng.post.model.PostWrapper;
import com.upup.demo.postsystem.ng.post.po.Post;
import com.upup.demo.postsystem.ng.post.dao.PostDao;
import com.upup.demo.postsystem.ng.post.model.PostQueryParam;
import com.upup.demo.postsystem.ng.post.service.PostService;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Post)表服务实现类
 *
 * @author generate by easycode
 * @since 2021-03-17 21:19:25
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PostWrapper findById(Integer id) {
        Post post = postDao.findById(id);
        PostWrapper postWrapper = mapPost(post);
        UserModel userModel = userService.findById(post.getUserId()).get();
        List<AnswerWrapper> answers = answerService.list(AnswerQueryParam.builder().postIds(new int[] {post.getId()}).build());
        postWrapper.setUserName(userModel.getName());
        postWrapper.setAnswers(answers);
        return postWrapper;
    }

    /**
     * 查询指定行数据
     *
     * @param param 查询参数对象
     * @return 对象列表
     */
    @Override
    public List<PostWrapper> list(PostQueryParam param) {
        List<PostWrapper> posts = postDao.list(param).stream().map(post -> {
            return mapPost(post);
        }).collect(Collectors.toList());
        attactUserName(posts);
        attachAnswers(posts);
        return posts;
    }

    /**
     * 新增数据
     *
     * @param post 实例对象
     * @return 实例对象
     */
    @Override
    public Post insert(Post post) {
        this.postDao.insert(post);
        return post;
    }

    /**
     * 修改数据
     *
     * @param post 实例对象
     * @return 实例对象
     */
    @Override
    public Post update(Post post) {
        this.postDao.update(post);
        return post;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.postDao.deleteById(id) > 0;
    }

    /**
     *可以用beancopy，也可以直接用getter/setter
     */
    private PostWrapper mapPost(Post post) {
        PostWrapper postWrapper = PostWrapper.builder().build();
        BeanUtils.copyProperties(post, postWrapper);
        return postWrapper;
    }

    private void attactUserName(List<PostWrapper> posts) {
        int[] userIds = posts
            .stream()
            .mapToInt(post -> post.getUserId())
            .toArray();
        Map<Integer, UserModel> userMap = userService.findByIds(userIds)
            .stream()
            .collect(Collectors.toMap(UserModel::getId, userModel -> userModel));
        posts.stream().forEach(post -> {
            UserModel userModel = userMap.get(post.getUserId());
            if (userModel != null) {
                post.setUserName(userModel.getName());
            }
        });
    }

    private void attachAnswers(List<PostWrapper> posts) {
        int[] postIds = posts
            .stream()
            .mapToInt(PostWrapper::getId)
            .toArray();
        Map<Integer, List<AnswerWrapper>> answers = answerService
            .list(AnswerQueryParam.builder().postIds(postIds).build())
            .stream()
            .collect(Collectors.groupingBy(AnswerWrapper::getPostId));

        posts.forEach(post -> {
            if(answers.get(post.getId())!=null) {
                post.setAnswers(answers.get(post.getId()));
            }else {
                post.setAnswers(new ArrayList<>());
            }
        });
    }
}